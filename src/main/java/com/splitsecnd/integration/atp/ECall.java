package com.splitsecnd.integration.atp;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.LoggingLevel;
import com.nxttxn.vramel.Message;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.nxttxn.vramel.processor.aggregate.KeyedAggregation;
import com.nxttxn.vramel.processor.aggregate.KeyedBodyAggregationStrategy;
import com.splitsecnd.integration.atp.model.EmergencyEvent;
import com.splitsecnd.integration.atp.model.PositionTrace;

public class ECall extends FlowBuilder {
	
	private ObjectMapper mapper;
	
	public class SaveOriginalEvent implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject event = new JsonObject( exchange.getIn().getBody(String.class));
			JsonObject device = event.getObject("device");
			exchange.setProperty("Event", event);
			exchange.getOut().setBody(new byte[] {});
			exchange.getOut().setHeaders(exchange.getIn().getHeaders());
			exchange.getOut().removeHeader(Exchange.HTTP_QUERY);
			exchange.getOut().setHeader("deviceId", URLEncoder.encode(URLEncoder.encode(device.getObject("splitsecnd").getString("id"))));
			exchange.getOut().setHeader("brand", device.getObject("brand"));
			
			logger.info("Setting deviceId header: {} is encoded as {}", device.getString("splitsecndId"), exchange.getOut().getHeader("deviceId"));
		}

	}

	@Override
	public void configure() throws Exception {
		mapper = new ObjectMapper();
		
        fromF("vertxQueue:ATPQueue")
        .log(LoggingLevel.DEBUG, ECall.class.getName(), "[ECall]: ${body}")
        .setHeader("usergridApp", simple("{{defaults.arc.usergridApp}}"))
        .onException(Exception.class).handled(true).log(LoggingLevel.ERROR, ECall.class.getName(), "Error").end()
        .toF("direct:{{bus-prefix}}/{{auth.service.name}}/getToken")
        .process(new SaveOriginalEvent())
        .toF("direct:getSubscription")
        .multicast(new KeyedBodyAggregationStrategy())
        .toF("direct:getVehicle")
        .toF("direct:getOwner")
        .end()
        .process(new ECallMessageTransformer(getResolvedConfig().getString("defaults.arc.project-code")))
        .choice()
        .when().simple("${header.brand.configuration.emergencyServiceConfigured} == true")
        	.routingSlip(
        		simple("rest:POST:${header.brand.configuration.requestUri}?host=${header.brand.configuration.host}&port=${header.brand.configuration.port}&ssl=${header.brand.configuration.ssl}&username=${header.brand.configuration.username}&password=${header.brand.configuration.password}"))
        	.end()
	    .otherwise()
	        .toF("rest:POST:{{defaults.arc.requestUri}}", 
	        	 getConfigObject("defaults.arc.http-connection-config"))
	    .endChoice()
        .toF("vertx:splitsecnd.dbUpdater");        
	}


	protected class ECallMessageTransformer implements Processor {

		private String defaultProjectCode;
		
		public ECallMessageTransformer(String projectCode) {
			this.defaultProjectCode = projectCode;
		}
		
		@Override
		public void process(Exchange exchange) throws Exception {
			KeyedAggregation results = exchange.getIn().getBody(KeyedAggregation.class);
			JsonObject event = exchange.getProperty("Event",JsonObject.class);
			JsonObject device = event.getObject("device");
			JsonObject brand = device.getObject("brand");
			JsonObject motorClub = null;
			try {
				motorClub = results.get("Owner");
				motorClub = motorClub.getObject("MotorClub");
			} catch( Exception e ) {
				logger.warn("no motorclub found - must default", e);
				motorClub = new JsonObject();				
			}
			JsonObject deviceOwner = null;
			try {
				deviceOwner = results.get("Owner");
				deviceOwner = deviceOwner.getObject("Owner");
			} catch( Exception e ) {
				logger.warn("no owner found - must default", e);
				deviceOwner = new JsonObject();				
			}
			JsonObject vehicle = null;
			try {
				vehicle = getJson((byte[])results.get("Vehicle"));
			} catch( Exception e ) {
				logger.warn("no vehicle found - must default", e);
				vehicle = new JsonObject();
			}
			EmergencyEvent ATPevent = EmergencyEvent.getInstance();
			ATPevent.getService().setAssistanceType("ECALL");
			ATPevent.getService().setProjectCode(
					(brand == null ? defaultProjectCode : 
						(brand.getObject("configuration") == null ? defaultProjectCode :
							StringUtils.defaultIfEmpty(brand.getObject("configuration").getString("projectCode"), defaultProjectCode))));
			ATPevent.getService().setServiceIdentifier(StringUtils.defaultIfEmpty(motorClub.getString("serviceHomeId"),"GB"));
			ATPevent.getEvent().setActivationMethod("Manual");
			ATPevent.getEvent().setCallerId(device.getString("phoneNumber"));
			ATPevent.getEvent().getCaller().setCallbackPhoneNumber("");
			ATPevent.getEvent().getCaller().setFirstName(StringUtils.defaultIfEmpty(deviceOwner.getString("firstName"), StringUtils.defaultIfEmpty(deviceOwner.getString("name"), "No First Name")));
			ATPevent.getEvent().getCaller().setLastName(StringUtils.defaultIfEmpty(deviceOwner.getString("lastName"), "No Last Name"));
			ATPevent.getEvent().getLocation().setCreationTimestamp(DateFormatUtils.format(new Date(event.getLong("time")), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC")));
			ATPevent.getEvent().getLocation().setAltitude(event.getLong("altitude"));
			ATPevent.getEvent().getLocation().setHeading(event.getInteger("heading"));
			ATPevent.getEvent().getLocation().setNumberOfSatellites(event.getInteger("satellites"));
			ATPevent.getEvent().getLocation().setSpeed(event.getInteger("speed"));
			float hdop = 0.0F;
			if (event.getNumber("hdop") != null) {
				hdop = event.getNumber("hdop").floatValue();
			}
			ATPevent.getEvent().getLocation().setIsPositionTrustable((hdop < 8 ? true : false));	
			ATPevent.getEvent().getLocation().getPosition().setLatitude((Double)event.getField("eventLatitude"));
			ATPevent.getEvent().getLocation().getPosition().setLongitude((Double)event.getField("eventLongitude"));
			JsonArray positions = event.getArray("pathData");
			if (positions != null) {
				Iterator<Object> it = positions.iterator();
				while(it.hasNext()) {
					JsonObject position = (JsonObject) it.next();
					PositionTrace positionTrace = new PositionTrace();
					positionTrace.setLatitude(position.getNumber("latitude").doubleValue());
					positionTrace.setLongitude(position.getNumber("longitude").doubleValue());
					ATPevent.getEvent().getLocation().getPositionTraces().add(positionTrace);
				}
			}
			ATPevent.getEvent().getVehicle().setColor(StringUtils.defaultIfEmpty(vehicle.getString("color"), ""));
			ATPevent.getEvent().getVehicle().setLicencePlate(StringUtils.defaultIfEmpty(vehicle.getString("licensePlate"), ""));
			ATPevent.getEvent().getVehicle().getVin().setVin(StringUtils.defaultIfEmpty(vehicle.getString("vin"),"12345678901234567")); ;
			Message out = exchange.getIn().copy();
			String atpJson = mapper.writeValueAsString(ATPevent);
	        out.setBody(atpJson);
	        exchange.setProperty("eventJson", atpJson);
	        out.setHeader("ein", device.getString("ein"));
	        out.removeHeader("Authorization");
	        exchange.setOut(out);			
		}
		
	}

	private JsonObject getJson(byte[] json) throws Exception {
		JsonObject j = new JsonObject(new String(json));
		JsonArray entities = j.getArray("entities");
		if (entities == null || entities.size() == 0) {
			throw new Exception("No entities for supplied json: " + j.toString());
		}
		
		return entities.get(0);
	}
}
