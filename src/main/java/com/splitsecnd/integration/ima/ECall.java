package com.splitsecnd.integration.ima;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.fasterxml.jackson.xml.XmlMapper;
import com.google.gson.Gson;
import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.LoggingLevel;
import com.nxttxn.vramel.Message;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.nxttxn.vramel.processor.aggregate.KeyedAggregation;
import com.nxttxn.vramel.processor.aggregate.KeyedBodyAggregationStrategy;
import com.splitsecnd.integration.atp.model.PositionTrace;
import com.splitsecnd.integration.ima.model.Call;
import com.splitsecnd.integration.ima.model.Caller;
import com.splitsecnd.integration.ima.model.Contact;
import com.splitsecnd.integration.ima.model.ContractualContext;
import com.splitsecnd.integration.ima.model.EcallRequest;
import com.splitsecnd.integration.ima.model.Location;
import com.splitsecnd.integration.ima.model.LocationHeader;
import com.splitsecnd.integration.ima.model.Request;
import com.splitsecnd.integration.ima.model.RequestObject;
import com.splitsecnd.integration.ima.model.Vehicle;
import com.splitsecnd.integration.ima.model.VehicleAccidentContext;

public class ECall extends FlowBuilder {
		
	private XmlMapper mapper;

	public class SaveOriginalEvent implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject event = new JsonObject( exchange.getIn().getBody(String.class));
			JsonObject device = event.getObject("device_");
			exchange.setProperty("Event", event);
			exchange.getOut().setBody(new byte[] {});
			exchange.getOut().setHeaders(exchange.getIn().getHeaders());
			exchange.getOut().removeHeader(Exchange.HTTP_QUERY);
			exchange.getOut().setHeader("deviceId", URLEncoder.encode(URLEncoder.encode(device.getString("splitsecndId_"))));
			
			logger.info("Setting deviceId header: {} is encoded as {}", device.getString("splitsecndId_"), exchange.getOut().getHeader("deviceId"));
		}

	}


	@Override
	public void configure() throws Exception {
		mapper = new XmlMapper();
		
		fromF("vertxQueue:IMAQueue")
        .log(LoggingLevel.DEBUG, ECall.class.getName(), "[ECall]: ${body}")
        .onException(Exception.class).handled(true).log(LoggingLevel.ERROR, ECall.class.getName(), "Error").end()
        .toF("direct:{{bus-prefix}}/{{auth.service.name}}/getToken")
        .process(new SaveOriginalEvent())
        .toF("direct:getSubscription")
        .multicast(new KeyedBodyAggregationStrategy())
        .toF("direct:getVehicle")
        .toF("direct:getOwner")
        .end()
        .process(new ECallMessageTransformer(getResolvedConfig().getString("ima.sourcePlatform"),
        		getResolvedConfig().getString("ima.targetPlatform"),
        		getResolvedConfig().getString("ima.clientCompanyCode"))
        )
        .toF("rest:POST:{{ima.requestUri}}", 
        	 getConfigObject("ima.http-connection-config")
        ).toF("vertx:splitsecnd.dbUpdater");
        
	}


	protected class ECallMessageTransformer implements Processor {

		private String sourcePlatform;
		private String targetPlatform;
		private String clientCompanyCode;
		
		public ECallMessageTransformer(String sourcePlatform, String targetPlatform, String clientCompanyCode) {
			this.sourcePlatform = sourcePlatform;
			this.targetPlatform = targetPlatform;
			this.clientCompanyCode = clientCompanyCode;
		}
		
		@Override
		public void process(Exchange exchange) throws Exception {
			KeyedAggregation results = exchange.getIn().getBody(KeyedAggregation.class);
			JsonObject event = exchange.getProperty("Event",JsonObject.class);
			JsonObject device = event.getObject("device_");
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
			
			Call call = new Call();
			call.setUidSupplier(""); //Put something here
			call.setSourcePlatformCode(sourcePlatform);
			call.setTargetPlatformCode(targetPlatform);
			call.setHardwareTimestamp(DateFormatUtils.format(new Date(event.getLong("time_")), "yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC")));
			
			Caller caller = new Caller();
			caller.setFirstname(StringUtils.defaultIfEmpty(deviceOwner.getString("firstName"), "No First Name"));
			caller.setName(StringUtils.defaultIfEmpty(deviceOwner.getString("lastName"), StringUtils.defaultIfEmpty(deviceOwner.getString("name"), "No Last Name")));
			caller.setPhoneNumber(device.getString("phoneNumber"));
			caller.setEmail("");
			caller.setFavoriteContactMean("TL");
			call.setCaller(caller);
			
			ContractualContext context = new ContractualContext();
			context.setClientCompanyCode(clientCompanyCode);
			call.setContractualContext(context);
			
			EcallRequest request = new EcallRequest();
			request.setAutomatic((event.getInteger("cause_") == 1));
			call.setRequest(request);
			
			Vehicle IMAvehicle = new Vehicle();
			IMAvehicle.setRegistration(StringUtils.defaultIfEmpty(vehicle.getString("licensePlate"), ""));
			IMAvehicle.setVehicleIdentification(StringUtils.defaultIfEmpty(vehicle.getString("vin"),"12345678901234567"));
			IMAvehicle.setColor(StringUtils.defaultIfEmpty(vehicle.getString("Color"), ""));
			IMAvehicle.setMake(StringUtils.defaultIfEmpty(vehicle.getString("Make"), ""));
			IMAvehicle.setModel(StringUtils.defaultIfEmpty(vehicle.getString("Model"), ""));
			request.setRequestObject(IMAvehicle);
			
			VehicleAccidentContext IMAcontext = new VehicleAccidentContext();
			IMAcontext.setCrash((event.getInteger("cause_") == 1));
			request.setContextData(IMAcontext);
			
			LocationHeader locationHeader = new LocationHeader();
			locationHeader.setProjectionSystemCode("1");
			locationHeader.setLocationConfidence(event.getNumber("hdop").floatValue() > 8 ? "U" : "V");
			locationHeader.setAltitudeUnit("M");
			locationHeader.setGpsAccuracyUnit("M");
			//hdop * 3m (3m is standard gps error)
			locationHeader.setGpsAccuracy(Float.toString(event.getNumber("hdop").floatValue() * 3));
			locationHeader.setLastLocationTimestamp(DateFormatUtils.format(new Date(event.getLong("time_")), "yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone("UTC")));
			JsonArray positions = event.getArray("pathData_");
			if (positions != null) {
				Iterator<Object> it = positions.iterator();
				while(it.hasNext()) {
					JsonObject position = (JsonObject) it.next();
					Location location = new Location();
					location.setLatitude( Long.toString(new Double(position.getNumber("lat_").doubleValue() * Math.pow(10,7)).longValue()));
					location.setLongitude(Long.toString(new Double(position.getNumber("lon_").doubleValue() * Math.pow(10,7)).longValue()));
					location.setDirection(position.getNumber("heading").intValue());
					locationHeader.getLocations().add(location);
				}
			}
			call.setLocation(locationHeader);
			
			Contact contact = new Contact();
			contact.setContact(device.getString("phoneNumber"));
			contact.setType("TL");
			call.setInitialContact(contact);
			
/*			EmergencyEvent ATPevent = new EmergencyEvent();
			ATPevent.getService().setAssistanceType("ECALL");
			//ATPevent.getService().setProjectCode(projectCode);
			//need to get this from customer...
			ATPevent.getService().setServiceIdentifier(StringUtils.defaultIfEmpty(motorClub.getString("serviceHomeId"),"GB"));
			ATPevent.getEvent().setActivationMethod("Manual");
			ATPevent.getEvent().setCallerId(deviceEvent.getString("phoneNumber"));
			ATPevent.getEvent().getCaller().setCallbackPhoneNumber("");
			ATPevent.getEvent().getCaller().setFirstName(StringUtils.defaultIfEmpty(deviceOwner.getString("firstName"), StringUtils.defaultIfEmpty(deviceOwner.getString("name"), "No First Name")));
			ATPevent.getEvent().getCaller().setLastName(StringUtils.defaultIfEmpty(deviceOwner.getString("lastName"), "No Last Name"));
			ATPevent.getEvent().getLocation().setCreationTimestamp(DateFormatUtils.format(new Date(deviceEvent.getLong("eventTimestamp")), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC")));
			ATPevent.getEvent().getLocation().getPosition().setLatitude((Double)deviceEvent.getField("eventLatitude"));
			ATPevent.getEvent().getLocation().getPosition().setLongitude((Double)deviceEvent.getField("eventLongitude"));
			ATPevent.getEvent().getVehicle().setColor(StringUtils.defaultIfEmpty(vehicle.getString("Color"), ""));
			ATPevent.getEvent().getVehicle().setLicencePlate(StringUtils.defaultIfEmpty(vehicle.getString("licensePlate"), ""));
			ATPevent.getEvent().getVehicle().getVin().setWmi(StringUtils.defaultIfEmpty(vehicle.getString("vin"),"12345678901234567").substring(0,3));
			ATPevent.getEvent().getVehicle().getVin().setVds(StringUtils.defaultIfEmpty(vehicle.getString("vin"),"12345678901234567").substring(3,9));
			ATPevent.getEvent().getVehicle().getVin().setVis(StringUtils.defaultIfEmpty(vehicle.getString("vin"),"12345678901234567").substring(9));
*/			Message out = exchange.getIn().copy();
			String imaXML = mapper.writeValueAsString(call);
	        out.setBody(imaXML);
	        exchange.setProperty("eventJson", imaXML);
	        out.setHeader("ein", device.getString("ein"));
	        out.removeHeader("Authorization");
	        out.setHeader("Content-Type", "application/xml");
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
