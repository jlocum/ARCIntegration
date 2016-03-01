package com.splitsecnd.integration.drivensolutions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.xml.XmlMapper;
import com.google.gson.Gson;
import com.nxttxn.vramel.Exchange;
import com.nxttxn.vramel.LoggingLevel;
import com.nxttxn.vramel.Message;
import com.nxttxn.vramel.Processor;
import com.nxttxn.vramel.builder.FlowBuilder;
import com.nxttxn.vramel.processor.aggregate.KeyedAggregation;
import com.nxttxn.vramel.processor.aggregate.KeyedBodyAggregationStrategy;
import com.splitsecnd.integration.atp.model.EmergencyEvent;
import com.splitsecnd.integration.atp.model.PositionTrace;
import com.splitsecnd.integration.drivensolutions.model.DispatchModel;
import com.splitsecnd.integration.ima.BrandConfiguration;
import com.splitsecnd.integration.usergrid.Usergrid.AggregateOwnerAndMotorClub;

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
			
			logger.info("Setting deviceId header: {} is encoded as {}", device.getString("splitsecndId"), exchange.getOut().getHeader("deviceId"));
		}

	}

	public class GoogleReverseGeocode implements Processor {

		@Override
		public void process(Exchange exchange) throws Exception {
			JsonObject googleResults = new JsonObject( exchange.getIn().getBody(String.class));
			JsonObject detailedResult = googleResults.getArray("results").get(0);
			
			GoogleLocationResult locationResult = new GoogleLocationResult();
			locationResult.setStreetNumber(getStreetNumber(detailedResult));
			locationResult.setAddress(getAddress(detailedResult));
			locationResult.setCity(getCity(detailedResult));
			locationResult.setState(getState(detailedResult));
			locationResult.setCountry("01");

			exchange.getIn().setBody(mapper.writeValueAsString(locationResult));
			exchange.getOut().setBody(mapper.writeValueAsString(locationResult));
		}
	}
	
	private String getStreetNumber(JsonObject json) throws Exception {
		return getGoogleLocationValue(json, "street_number");
	}

	private String getAddress(JsonObject json) throws Exception {
		return getGoogleLocationValue(json, "route");
	}

	private String getCity(JsonObject json) throws Exception {
		return getGoogleLocationValue(json, "locality");
	}

	private String getState(JsonObject json) throws Exception {
		return getGoogleLocationValue(json, "administrative_area_level_1");
	}

	private String getGoogleLocationValue(JsonObject json, String typeKey) throws Exception {
		JsonArray addressComponents = json.getArray("address_components");
		if (addressComponents == null || addressComponents.size() == 0) {
			throw new Exception("No addressComponents for supplied json: " + json.toString());
		}
		
		for(int i=0; i < addressComponents.size(); i++) {
			JsonObject addressComponent = addressComponents.get(i);
			JsonArray addressComponentTypes = addressComponent.getArray("types");
			for(int j=0; j < addressComponentTypes.size(); j++) {
				if (StringUtils.equals((String) addressComponentTypes.get(j), typeKey)) {
					return addressComponent.getString("short_name");
				}
			}
		}
		return "Not Found";
	}
	
	@Override
	public void configure() throws Exception {
		mapper = new XmlMapper();
		
        fromF("vertxQueue:DrivenSolQueue")
        .log(LoggingLevel.DEBUG, ECall.class.getName(), "[ECall]: ${body}")
        .setHeader("usergridApp", simple("{{defaults.drivenSolutions.usergridApp}}"))
        .setProperty("clientId", simple("{{defaults.drivenSolutions.clientId}}"))
        .setProperty("clientPassword", simple("{{defaults.drivenSolutions.clientPassword}}"))
        .onException(Exception.class).handled(true).log(LoggingLevel.ERROR, ECall.class.getName(), "Error").end()
        .toF("direct:{{bus-prefix}}/{{auth.service.name}}/usergrid/getToken")
        .process(new SaveOriginalEvent())
        .toF("direct:getSubscription")
        .multicast(new KeyedBodyAggregationStrategy())
        .toF("direct:getVehicle")
        .toF("direct:getOwner")
        .end()
        //.toF("direct:googleGeocode")
        .process(new RoadsideAssistanceMessageTransformer(getResolvedConfig().getString("defaults.drivenSolutions.clientId"), getResolvedConfig().getString("defaults.drivenSolutions.clientPassword")))
        //.end();
        .toF("direct:postToDrivenSolService").end();
		
		fromF("direct:postToDrivenSolService")
			.routingSlip(
        		simple("rest:POST:${property.requestUri}?host=${property.host}&port=${property.port}&ssl=${property.ssl}"))
    	.toF("vertx:splitsecnd.dbUpdater").end();    
		
/*		fromF("direct:googleGeocode")
        .onException(Throwable.class).process(new GoogleReverseGeocode())
    		.end()
    	.routingSlip("http://maps.google.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=false", config)
    	.routingSlip(simple("REST:GET:/maps/api/geocode/json?"))
*/	}


	protected class RoadsideAssistanceMessageTransformer implements Processor {

		private String clientId;
		private String clientPassword;
		
		public RoadsideAssistanceMessageTransformer(String clientId, String clientPassword) {
			this.clientId = clientId;
			this.clientPassword = clientPassword;
		}
		
		@Override
		public void process(Exchange exchange) throws Exception {
			KeyedAggregation results = exchange.getIn().getBody(KeyedAggregation.class);
			JsonObject subscription = exchange.getProperty("fullSubscription", JsonObject.class);
			JsonObject event = exchange.getProperty("Event",JsonObject.class);
			JsonObject device = event.getObject("device");
			JsonObject brand = device.getObject("brand");
			JsonObject brand_config = device.getObject("brand").getObject("configuration");
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
			GoogleLocationResult location = getLocationInfo((Double)event.getField("latitude"), (Double)event.getField("longitude"));
			
			DispatchModel dispatch = DispatchModel.getInstance(clientId, clientPassword);
			dispatch.setBreakdownStreetNumber(location.getStreetNumber());
			dispatch.setBreakdownAddress1(location.getAddress());
			dispatch.setBreakdowncity(location.getCity());
			dispatch.setBreakdowncountryCode("01");
			dispatch.setServiceType("Towing");
			String driverName = StringUtils.defaultIfEmpty(subscription.getString("driverName"), "");
			String[] nameParts = driverName.split(" ");
			dispatch.setFirstName(StringUtils.defaultIfEmpty(nameParts[0], "None Given"));
			dispatch.setLastName((nameParts.length > 1) ? StringUtils.join(Arrays.copyOfRange(nameParts, 1, nameParts.length), " ") :
					"None Given");
			dispatch.setPreferredPhoneNumber(device.getString("phoneNumber"));
			dispatch.setAlternatePhoneNumber(device.getString("phoneNumber"));
			dispatch.setDestinationProvinceOrState("02/01/2016");
			///put the dispatch stuff here
			Message out = exchange.getIn().copy();
			String dispatchXml = mapper.writeValueAsString(dispatch);
	        out.setBody(dispatchXml);
	        exchange.setProperty("eventJson", dispatchXml);
	        out.setHeader("ein", device.getString("ein"));
			BrandConfiguration brandConfig = new BrandConfiguration();
			if (brand != null) {
				brandConfig.populate(brand_config);
			}
			exchange.setProperty("requestUri", brandConfig.getRequestUri());
			exchange.setProperty("host", brandConfig.getHost());
			exchange.setProperty("port", brandConfig.getPort());
			exchange.setProperty("ssl", String.valueOf(brandConfig.isSsl()));
			exchange.setProperty("username", brand_config.getString("username"));
			exchange.setProperty("password", brand_config.getString("password"));
	        out.removeHeader("Authorization");
	        exchange.setOut(out);			
		}
		
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
	
	public GoogleLocationResult getLocationInfo( double lat, double lng) throws Exception {

	    HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=false");
	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response;
	    StringBuilder stringBuilder = new StringBuilder();

	    try {
	        response = client.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        InputStream stream = entity.getContent();
	        int b;
	        while ((b = stream.read()) != -1) {
	            stringBuilder.append((char) b);
	        }
	    } catch (ClientProtocolException e) {
	        } catch (IOException e) {
	    }

	    JsonObject googleResults = new JsonObject(stringBuilder.toString());
		JsonObject detailedResult = googleResults.getArray("results").get(0);
		
		GoogleLocationResult locationResult = new GoogleLocationResult();
		locationResult.setStreetNumber(getStreetNumber(detailedResult));
		locationResult.setAddress(getAddress(detailedResult));
		locationResult.setCity(getCity(detailedResult));
		locationResult.setState(getState(detailedResult));
		locationResult.setCountry("01");
	    
		return locationResult;
	}
	
	public class GoogleLocationResult {
		private String streetNumber;
		private String address;
		private String city;
		private String state;
		private String country;
		private String zip;
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getStreetNumber() {
			return streetNumber;
		}
		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}
	}
}
