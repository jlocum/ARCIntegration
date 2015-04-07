package com.splitsecnd.integration.ima;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.fasterxml.jackson.xml.XmlMapper;
import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.LoggingLevel;
import com.nxttxn.vramel.Message;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.nxttxn.vramel.processor.aggregate.KeyedAggregation;
import com.nxttxn.vramel.processor.aggregate.KeyedBodyAggregationStrategy;
import com.splitsecnd.integration.ima.model.Call;
import com.splitsecnd.integration.ima.model.Caller;
import com.splitsecnd.integration.ima.model.Contact;
import com.splitsecnd.integration.ima.model.ContractualContext;
import com.splitsecnd.integration.ima.model.EcallRequest;
import com.splitsecnd.integration.ima.model.Location;
import com.splitsecnd.integration.ima.model.LocationHeader;
import com.splitsecnd.integration.ima.model.Vehicle;
import com.splitsecnd.integration.ima.model.VehicleAccidentContext;

public class ECall extends FlowBuilder {
		
	private XmlMapper mapper;

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
			exchange.getOut().setHeader("brand", device.getObject("brand").getString("name").toLowerCase());
			
			logger.info("Setting deviceId header: {} is encoded as {}", device.getString("splitsecndId"), exchange.getOut().getHeader("deviceId"));
		}

	}


	@Override
	public void configure() throws Exception {
		mapper = new XmlMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		
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
			JsonObject device = event.getObject("device");
			JsonObject aggregate = new JsonObject(new String((byte[]) results.get("Owner")));
			JsonObject motorClub = null;
			try {
				motorClub = getEntity(aggregate.getObject("MotorClub"));
			} catch( Exception e ) {
				logger.warn("no motorclub found - must default", e);
				motorClub = new JsonObject();				
			}
			JsonObject deviceOwner = null;
			try {
				deviceOwner = getEntity(aggregate.getObject("Owner"));
			} catch( Exception e ) {
				logger.warn("no owner found - must default", e);
				deviceOwner = new JsonObject();				
			}
			JsonObject vehicle = null;
			try {
				vehicle = getEntity((byte[])results.get("Vehicle"));
			} catch( Exception e ) {
				logger.warn("no vehicle found - must default", e);
				vehicle = new JsonObject();
			}
			
			Call call = new Call();
			call.setUidSupplier(event.getLong("id") + "-" + event.getLong("time")); //Put something here
			call.setSourcePlatformCode(sourcePlatform);
			call.setTargetPlatformCode(targetPlatform);
			call.setHardwareTimestamp(DateFormatUtils.format(new Date(event.getLong("time")), "yyyy-MM-dd'T'HH:mm:ssZ", TimeZone.getTimeZone("UTC")));
			
			Caller caller = new Caller();
			caller.setFirstname(StringUtils.defaultIfEmpty(deviceOwner.getString("firstName"), ""));
			caller.setName(StringUtils.defaultIfEmpty(deviceOwner.getString("lastName"), StringUtils.defaultIfEmpty(deviceOwner.getString("name"), "No Last Name")));
			caller.setPhoneNumber(device.getString("phoneNumber"));
			caller.setEmail(StringUtils.defaultIfEmpty(deviceOwner.getString("email"), "none@given"));
			caller.setFavoriteContactMean("TL");
			call.setCaller(caller);
			
			ContractualContext context = new ContractualContext();
			context.setClientCompanyCode(clientCompanyCode);
			//placeholder
			context.setContractualId("-");
			call.setContractualContext(context);
			
			EcallRequest request = new EcallRequest();
			request.setAutomatic((event.getInteger("cause") == 1));
			call.setRequest(request);
			
			Vehicle IMAvehicle = new Vehicle();
			IMAvehicle.setRegistration(StringUtils.defaultIfEmpty(vehicle.getString("licensePlate"), ""));
			IMAvehicle.setVehicleIdentification(StringUtils.defaultIfEmpty(vehicle.getString("vin"),"12345678901234567"));
			IMAvehicle.setColor(StringUtils.defaultIfEmpty(vehicle.getString("color"), ""));
			IMAvehicle.setMake(StringUtils.defaultIfEmpty(vehicle.getString("make"), ""));
			IMAvehicle.setModel(StringUtils.defaultIfEmpty(vehicle.getString("model"), ""));
			request.setRequestObject(IMAvehicle);
			
			VehicleAccidentContext IMAcontext = new VehicleAccidentContext();
			IMAcontext.setCrash((event.getInteger("cause") == 1));
			request.setContextData(IMAcontext);
			
			float hdop = 0.0F;
			if (event.getNumber("hdop") != null) {
				hdop = event.getNumber("hdop").floatValue();
			}
			LocationHeader locationHeader = new LocationHeader();
			locationHeader.setProjectionSystemCode("1");
			locationHeader.setLocationConfidence(hdop > 8 ? "U" : "V");
			locationHeader.setAltitudeUnit("M");
			locationHeader.setGpsAccuracyUnit("M");
			//hdop * 3m (3m is standard gps error)
			locationHeader.setGpsAccuracy(Float.toString(hdop * 3));
			JsonArray positions = event.getArray("pathData");
			locationHeader.setLastLocationTimestamp(DateFormatUtils.format(new Date(event.getLong("time")), "yyyy-MM-dd'T'HH:mm:ssZ", TimeZone.getTimeZone("UTC")));
			if (positions != null) {
				Iterator<Object> it = positions.iterator();
				while(it.hasNext()) {
					JsonObject position = (JsonObject) it.next();
					Location location = new Location();
					location.setLatitude( Long.toString(new Double(position.getNumber("latitude").doubleValue() * Math.pow(10,7)).longValue()));
					location.setLongitude(Long.toString(new Double(position.getNumber("longitude").doubleValue() * Math.pow(10,7)).longValue()));
					location.setDirection(position.getNumber("heading").intValue());
					locationHeader.getLocations().add(location);
				}
			}
			call.setLocation(locationHeader);
			
			Contact contact = new Contact();
			contact.setContact(device.getString("phoneNumber"));
			contact.setType("TL");
			contact.setUidEquipement("-");
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
			//String imaXML = mapper.writeValueAsString(call);
			String imaXML = generateXML(call);
	        out.setBody(imaXML);
	        exchange.setProperty("eventJson", imaXML);
	        out.setHeader("ein", device.getString("ein"));
	        out.removeHeader("Authorization");
	        out.setHeader(Exchange.CONTENT_TYPE, "application/xml");
	        exchange.setOut(out);			
		}
		
	}
	
	private String generateXML(Call call) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JAXBContext jc = JAXBContext.newInstance(call.getClass());

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "classpath:ima/call_fr.tm.ima.not.telematic.call.xsd");
		marshaller.marshal(call, baos);

		return baos.toString();
	}

	private JsonObject getEntity(byte[] json) throws Exception {
		JsonObject j = new JsonObject(new String(json));
		return getEntity(j);
	}

	private JsonObject getEntity(JsonObject json) throws Exception {
		JsonArray entities = json.getArray("entities");
		if (entities == null || entities.size() == 0) {
			throw new Exception("No entities for supplied json: " + json.toString());
		}
		
		return entities.get(0);
	}
}
