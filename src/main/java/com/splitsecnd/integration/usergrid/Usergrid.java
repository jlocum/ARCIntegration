package com.splitsecnd.integration.usergrid;

import org.apache.commons.lang.StringUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.nxttxn.vramel.processor.aggregate.KeyedBodyAggregationStrategy;

public class Usergrid extends FlowBuilder {
	
	public class AggregateOwnerAndMotorClub implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject aggJson = new JsonObject();
			byte[] ownerBody = (byte[]) exchange.getProperty("ownerBody");
			byte[] motorClubBody = (byte[]) exchange.getProperty("motorClubBody");
			String owner = (ownerBody == null) ? null : new String(ownerBody);
			String motorClub = (motorClubBody == null) ? null : new String(motorClubBody);
			aggJson.putObject("Owner", (owner == null || owner.length() == 0) ? null : new JsonObject(owner));
			aggJson.putObject("MotorClub", (motorClub == null  || motorClub.length() == 0) ? null : new JsonObject(motorClub));

			exchange.getIn().setBody(aggJson.toString().getBytes());
			exchange.getOut().setBody(exchange.getIn().getBody());
		}

	}


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
			exchange.getOut().setHeaders(exchange.getIn().getHeaders());
			exchange.getOut().setHeader("motorclubUUID", deviceOwner.getString("motorClubId"));
			exchange.getOut().setBody(exchange.getIn().getBody());
		}

	}
	
	public class RetrieveSubscriptionUUID implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject usergridSubscription = new JsonObject( exchange.getIn().getBody(String.class));
			JsonArray subscriptionsFound = usergridSubscription.getArray("entities");
			if (subscriptionsFound == null || subscriptionsFound.size() == 0) {
				throw new Exception("No subscription found for " + exchange.getIn().getHeader("deviceId"));
			}
			if (subscriptionsFound.size() > 1) {
				logger.warn("More than one subscription found for {}", exchange.getIn().getHeader("deviceId"));
			}
			JsonObject subscription = getLatestSubscription(subscriptionsFound);
			exchange.getOut().setBody(new byte[] {});
			exchange.getOut().setHeaders(exchange.getIn().getHeaders());
			exchange.getOut().setHeader("subscriptionUUID", subscription.getString("uuid"));
			exchange.setProperty("fullSubscription", subscription);
			exchange.getOut().setHeader("vehicleUUID", subscription.getString("vehicleId"));
			if (StringUtils.isEmpty(subscription.getString("vehicleId"))) {
				logger.warn("No vehicleId found for {}", exchange.getIn().getHeader("deviceId"));
			}
			exchange.getOut().removeHeader(Exchange.HTTP_QUERY);
			
			logger.info("Setting headers: subscriptionUUID - {} , vehicleUUID - {}", subscription.getString("uuid"),subscription.getString("vehicleId"));

		}

		private JsonObject getLatestSubscription(JsonArray subscriptionsFound) {
			long mostRecentRecordTimestamp = 0;
			int indexOfMostRecentRecord = -1;
			JsonObject current = null;
			for(int i=0; i < subscriptionsFound.size(); i++) {
				current = subscriptionsFound.get(i);
				long currentRecordTimestamp = current.getLong("modified");
				if (currentRecordTimestamp > mostRecentRecordTimestamp) {
					mostRecentRecordTimestamp = currentRecordTimestamp;
					indexOfMostRecentRecord = i;
				}
			}
			logger.info("Most recent record of {} records has modified timestamp of {}", subscriptionsFound.size(), mostRecentRecordTimestamp);
			return subscriptionsFound.get(indexOfMostRecentRecord);
		}
	}

	private static String USERGRID_CONFIG = "host={{usergrid.host}}&port={{usergrid.port}}&ssl={{usergrid.ssl}}";
	private static String USERGRID_PATH = "/{{usergrid.org}}";
	private static String SUBSCRIPTIONS = "/{{subscriptions}}";
	private static String GET_VEHICLE_FOR_DEVICE = "/vehicles/${header.vehicleUUID}";
	private static String GET_OWNER_FOR_DEVICE = SUBSCRIPTIONS + "/${header.subscriptionUUID}/{{subscriptionUserConnection}}";
	private static String GET_MOTORCLUB_FOR_OWNER = "/motorclubs/${header.motorclubUUID}";

	@Override
	public void configure() throws Exception {

		fromF("direct:getSubscription")
		.setHeader("Authorization", simple("Bearer YWMtn_cjGt7sEeWC462Duy2UoIAAAVMtU3fA446pRxpaE3Mc7mewhZldhBxwTWc"))
        .setHeader(Exchange.HTTP_QUERY, simple("ql=select%20*%20where%20deviceId=%27${header.deviceId}%27"))
        .routingSlip(simple("rest:GET:" + USERGRID_PATH + "/${header.usergridApp}" + SUBSCRIPTIONS + "?" + USERGRID_CONFIG ))
        .process(new RetrieveSubscriptionUUID());
        
        fromF("direct:getVehicle")
        .setProperty(KeyedBodyAggregationStrategy.KEY, constant("Vehicle"))
        .choice()
        .when().simple("${header.vehicleUUID} != null")
        	.routingSlip(simple("rest:GET:" + USERGRID_PATH + "/${header.usergridApp}" + GET_VEHICLE_FOR_DEVICE + "?" + USERGRID_CONFIG)).end()
        .otherwise()
        	.setBody(constant(new byte[]{}))
        .endChoice();
        
        fromF("direct:getOwner")
        .onException(Throwable.class).process(new AggregateOwnerAndMotorClub())
        	.end()
        .setProperty(KeyedBodyAggregationStrategy.KEY, constant("Owner"))
        .routingSlip(simple("rest:GET:" + USERGRID_PATH + "/${header.usergridApp}" + GET_OWNER_FOR_DEVICE + "?" + USERGRID_CONFIG))
        .process(new SaveMotorClubUUID())
        .setProperty("ownerBody", body())
        .setBody(constant(new byte[]{}))
        .choice()
        .when().simple("${header.motorclubUUID} != null")
        	.routingSlip(simple("rest:GET:" + USERGRID_PATH + "/${header.usergridApp}" + GET_MOTORCLUB_FOR_OWNER + "?" + USERGRID_CONFIG)).end()
        .otherwise()
        	.setBody(constant(new byte[]{}))
        .endChoice()
        .setProperty("motorClubBody", body())
        .process(new AggregateOwnerAndMotorClub());
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
