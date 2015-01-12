package com.splitsecnd.integration.atp;

import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.vertx.java.core.json.JsonObject;

import com.google.gson.Gson;
import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.LoggingLevel;
import com.nxttxn.vramel.Message;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.splitsecnd.integration.atp.model.EmergencyEvent;

public class ECall extends FlowBuilder {
	
    public static final String PROJECT_CODE = "{{project_code}}";

	@Override
	public void configure() throws Exception {
        fromF("vertxQueue:ATPQueue")
        .log(LoggingLevel.DEBUG, ECall.class.getName(), "[ECall]: ${body}")
        .onException(Exception.class).handled(true).log(LoggingLevel.ERROR, ECall.class.getName(), "Error").end()
        .process(new ECallMessageTransformer())
        .toF("rest:POST:{{requestUri}}", 
        	 new JsonObject()
        			.putString("host", "{{host}}")
        			.putNumber("port", 443)
        			.putBoolean("ssl", true)
        			.putString("username","{{username}}")
        			.putString("password", "{{password}}")
        ).process(new Processor() {

			@Override
			public void process(Exchange arg0) throws Exception {
				System.out.println(arg0.getIn().getBody(String.class));
				
			}});
        		
	}


	protected class ECallMessageTransformer implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject deviceEvent = new JsonObject(exchange.getIn().getBody(String.class));
			EmergencyEvent ATPevent = new EmergencyEvent();
			ATPevent.getService().setAssistanceType("ECALL");
			ATPevent.getService().setProjectCode(PROJECT_CODE);
			//need to get this from customer...
			ATPevent.getService().setServiceIdentifier(deviceEvent.getObject("vehicle").getObject("owner").getString("serviceHomeId"));
			ATPevent.getEvent().setActivationMethod("Manual");
			ATPevent.getEvent().setCallerId(deviceEvent.getString("phoneNumber"));
			ATPevent.getEvent().getCaller().setCallbackPhoneNumber("");
			ATPevent.getEvent().getCaller().setFirstName(deviceEvent.getObject("vehicle").getObject("owner").getString("firstName"));
			ATPevent.getEvent().getCaller().setLastName(deviceEvent.getObject("vehicle").getObject("owner").getString("lastName"));
			ATPevent.getEvent().getLocation().setCreationTimestamp(DateFormatUtils.format(new Date(deviceEvent.getLong("eventTimestamp")), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC")));
			ATPevent.getEvent().getLocation().getPosition().setLatitude((Double)deviceEvent.getField("eventLatitude"));
			ATPevent.getEvent().getLocation().getPosition().setLongitude((Double)deviceEvent.getField("eventLongitude"));
			ATPevent.getEvent().getVehicle().setColor("");
			ATPevent.getEvent().getVehicle().setLicencePlate("");
			ATPevent.getEvent().getVehicle().getVin().setWmi(deviceEvent.getObject("vehicle").getString("VIN").substring(0,3));
			ATPevent.getEvent().getVehicle().getVin().setVds(deviceEvent.getObject("vehicle").getString("VIN").substring(3,9));
			ATPevent.getEvent().getVehicle().getVin().setVis(deviceEvent.getObject("vehicle").getString("VIN").substring(9));
			Message out = exchange.getIn().copy();
	        out.setBody(new Gson().toJson(ATPevent));
	        exchange.setOut(out);			
		}
		
	}
}
