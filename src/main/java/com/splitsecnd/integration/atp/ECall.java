package com.splitsecnd.integration.atp;

import java.net.URLEncoder;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.google.gson.Gson;
import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.LoggingLevel;
import com.nxttxn.vramel.Message;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.nxttxn.vramel.processor.aggregate.KeyedAggregation;
import com.nxttxn.vramel.processor.aggregate.KeyedBodyAggregationStrategy;
import com.splitsecnd.integration.atp.model.EmergencyEvent;

public class ECall extends FlowBuilder {
	
	public class SaveMotorClubJson implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject motorClub = getJson(exchange.getIn().getBody(byte[].class));
			exchange.setProperty("motorclub", motorClub);
		}
	}


	public class SaveMotorClubUUID implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject deviceOwner = getJson(exchange.getIn().getBody(byte[].class));
			exchange.setProperty("motorclubUUID", deviceOwner.getString("motorClubId"));
			exchange.getOut().setBody(exchange.getIn().getBody());
		}

	}


	public class RetrieveSubscriptionUUID implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject usergridSubscription = new JsonObject( exchange.getIn().getBody(String.class));
			JsonArray subscriptionsFound = usergridSubscription.getArray("entities");
			if (subscriptionsFound.size() > 1) {
				throw new Exception("More than one subscription found for " + exchange.getIn().getHeader("deviceId"));
			}
			if (subscriptionsFound.size() == 0) {
				throw new Exception("No subscription found for " + exchange.getIn().getHeader("deviceId"));
			}
			JsonObject subscription = subscriptionsFound.get(0);
			exchange.getOut().setBody(new byte[] {});
			exchange.getOut().setHeaders(exchange.getIn().getHeaders());
			exchange.getOut().setHeader("subscriptionUUID", subscription.getString("uuid"));
			exchange.getOut().setHeader("vehicleUUID", subscription.getString("vehicleId"));
			if (StringUtils.isEmpty(subscription.getString("vehicleId"))) {
				throw new Exception("No vehicleId found for " + exchange.getIn().getHeader("deviceId"));
			}
			exchange.getOut().removeHeader(Exchange.HTTP_QUERY);
			
			logger.info("Setting headers: subscriptionUUID - {} , vehicleUUID - {}", subscription.getString("uuid"),subscription.getString("vehicleId"));

		}
	}


	public class SaveOriginalEvent implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject deviceEvent = new JsonObject( exchange.getIn().getBody(String.class));
			exchange.setProperty("Event", deviceEvent);
			exchange.getOut().setBody(new byte[] {});
			exchange.getOut().setHeaders(exchange.getIn().getHeaders());
			exchange.getOut().removeHeader(Exchange.HTTP_QUERY);
			exchange.getOut().setHeader("deviceId", URLEncoder.encode(deviceEvent.getString("splitsecndId_")));
			
			logger.info("Setting deviceId header: {} is encoded as {}", deviceEvent.getString("splitsecndId_"), exchange.getOut().getHeader("deviceId"));
		}

	}


	private static String USERGRID_CONFIG = "host={{usergrid.host}}&port={{usergrid.port}}&ssl={{usergrid.ssl}}";
	private static String USERGRID_PATH = "/{{usergrid.org}}/{{usergrid.app}}";
	private static String SUBSCRIPTIONS = USERGRID_PATH + "/{{subscriptions}}";
	private static String GET_VEHICLE_FOR_DEVICE = USERGRID_PATH + "/vehicles/${header.vehicleUUID}";
	private static String GET_OWNER_FOR_DEVICE = SUBSCRIPTIONS + "/${header.subscriptionUUID}/{{subscriptionUserConnection}}";
	private static String GET_MOTORCLUB_FOR_OWNER = USERGRID_PATH + "motorclubs/${header.motorclubUUID}";

	@Override
	public void configure() throws Exception {
        fromF("vertxQueue:ATPQueue")
        .log(LoggingLevel.DEBUG, ECall.class.getName(), "[ECall]: ${body}")
        .onException(Exception.class).handled(true).log(LoggingLevel.ERROR, ECall.class.getName(), "Error").end()
        .toF("direct:{{bus-prefix}}/{{auth.service.name}}/getToken")
        .process(new SaveOriginalEvent())
        .toF("direct:getSubscription")
        .multicast(new KeyedBodyAggregationStrategy())
        .toF("direct:getVehicle")
        .toF("direct:getOwner")
        .end()
        .toF("direct:getMotorClub")
        .process(new ECallMessageTransformer(getResolvedConfig().getString("project-code")))
        .toF("rest:POST:{{requestUri}}", 
        	 getConfigObject("http-connection-config")
        ).toF("vertx:splitsecnd.dbUpdater");
        
        fromF("direct:getSubscription")
        .setHeader(Exchange.HTTP_QUERY, simple("ql=select%20*%20where%20deviceId=%27${header.deviceId}%27"))
        .toF("rest:GET:" + SUBSCRIPTIONS + "?" + USERGRID_CONFIG )
        .process(new RetrieveSubscriptionUUID());
        
        fromF("direct:getVehicle")
        .setProperty(KeyedBodyAggregationStrategy.KEY, constant("Vehicle"))
        .routingSlip(simple("rest:GET:" + GET_VEHICLE_FOR_DEVICE + "?" + USERGRID_CONFIG));
        
        fromF("direct:getOwner")
        .setProperty(KeyedBodyAggregationStrategy.KEY, constant("Owner"))
        .routingSlip(simple("rest:GET:" + GET_OWNER_FOR_DEVICE + "?" + USERGRID_CONFIG))
        .process(new SaveMotorClubUUID());
          		
        fromF("direct:getMotorClub")
        .setProperty("saveThisBody", body())
        .routingSlip(simple("rest:GET:" + GET_MOTORCLUB_FOR_OWNER + "?" + USERGRID_CONFIG))
        .process(new SaveMotorClubJson())
        .setBody(property("saveThisBody"));      
	}


	protected class ECallMessageTransformer implements Processor {

		private String projectCode;
		
		public ECallMessageTransformer(String projectCode) {
			this.projectCode = projectCode;
		}
		
		@Override
		public void process(Exchange exchange) throws Exception {
			KeyedAggregation results = exchange.getIn().getBody(KeyedAggregation.class);
			JsonObject deviceEvent = exchange.getProperty("Event",JsonObject.class);
			JsonObject motorClub = exchange.getProperty("motorclub",JsonObject.class);
			JsonObject deviceOwner = getJson((byte[]) results.get("Owner"));
			JsonObject vehicle = getJson((byte[])results.get("Vehicle"));
			EmergencyEvent ATPevent = new EmergencyEvent();
			ATPevent.getService().setAssistanceType("ECALL");
			ATPevent.getService().setProjectCode(projectCode);
			//need to get this from customer...
			ATPevent.getService().setServiceIdentifier(StringUtils.defaultIfEmpty(motorClub.getString("serviceHomeId"),"GB"));
			ATPevent.getEvent().setActivationMethod("Manual");
			ATPevent.getEvent().setCallerId(deviceEvent.getString("phoneNumber"));
			ATPevent.getEvent().getCaller().setCallbackPhoneNumber("");
			ATPevent.getEvent().getCaller().setFirstName(deviceOwner.getString("firstName"));
			ATPevent.getEvent().getCaller().setLastName(deviceOwner.getString("lastName"));
			ATPevent.getEvent().getLocation().setCreationTimestamp(DateFormatUtils.format(new Date(deviceEvent.getLong("eventTimestamp")), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC")));
			ATPevent.getEvent().getLocation().getPosition().setLatitude((Double)deviceEvent.getField("eventLatitude"));
			ATPevent.getEvent().getLocation().getPosition().setLongitude((Double)deviceEvent.getField("eventLongitude"));
			ATPevent.getEvent().getVehicle().setColor(StringUtils.defaultIfEmpty(vehicle.getString("Color"), ""));
			ATPevent.getEvent().getVehicle().setLicencePlate(StringUtils.defaultIfEmpty(vehicle.getString("licensePlate"), ""));
			ATPevent.getEvent().getVehicle().getVin().setWmi(vehicle.getString("vin").substring(0,3));
			ATPevent.getEvent().getVehicle().getVin().setVds(vehicle.getString("vin").substring(3,9));
			ATPevent.getEvent().getVehicle().getVin().setVis(vehicle.getString("vin").substring(9));
			Message out = exchange.getIn().copy();
			String atpJson = new Gson().toJson(ATPevent);
	        out.setBody(atpJson);
	        exchange.setProperty("eventJson", atpJson);
	        out.setHeader("ein", deviceEvent.getString("ein"));
	        out.removeHeader("Authorization");
	        exchange.setOut(out);			
		}
		
	}

	private JsonObject getJson(byte[] json) throws Exception {
		JsonObject j = new JsonObject(new String(json));
		JsonArray entities = j.getArray("entities");
		if (entities.size() == 0) {
			throw new Exception("No entities for supplied json: " + j.toString());
		}
		
		return entities.get(0);
	}
}
