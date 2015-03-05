
package com.splitsecnd.integration.atp.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "ActivationMethod",
    "Caller",
    "CallerId",
    "Correlation",
    "Device",
    "Location",
    "Type",
    "Vehicle"
})
public class Event {

    @JsonProperty("ActivationMethod")
    private String ActivationMethod;
    @JsonProperty("Caller")
    private com.splitsecnd.integration.atp.model.Caller caller;
    @JsonProperty("CallerId")
    private String CallerId;
    @JsonProperty("Correlation")
    private com.splitsecnd.integration.atp.model.Correlation correlation;
    @JsonProperty("Device")
    private com.splitsecnd.integration.atp.model.Device device;
    @JsonProperty("Location")
    private com.splitsecnd.integration.atp.model.Location location;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("Vehicle")
    private com.splitsecnd.integration.atp.model.Vehicle vehicle;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ActivationMethod
     */
    @JsonProperty("ActivationMethod")
    public String getActivationMethod() {
        return ActivationMethod;
    }

    /**
     * 
     * @param ActivationMethod
     *     The ActivationMethod
     */
    @JsonProperty("ActivationMethod")
    public void setActivationMethod(String ActivationMethod) {
        this.ActivationMethod = ActivationMethod;
    }

    /**
     * 
     * @return
     *     The Caller
     */
    @JsonProperty("Caller")
    public com.splitsecnd.integration.atp.model.Caller getCaller() {
        return caller;
    }

    /**
     * 
     * @param caller
     *     The Caller
     */
    @JsonProperty("Caller")
    public void setCaller(com.splitsecnd.integration.atp.model.Caller caller) {
        this.caller = caller;
    }

    /**
     * 
     * @return
     *     The CallerId
     */
    @JsonProperty("CallerId")
    public String getCallerId() {
        return CallerId;
    }

    /**
     * 
     * @param CallerId
     *     The CallerId
     */
    @JsonProperty("CallerId")
    public void setCallerId(String CallerId) {
        this.CallerId = CallerId;
    }

    /**
     * 
     * @return
     *     The Correlation
     */
    @JsonProperty("Correlation")
    public com.splitsecnd.integration.atp.model.Correlation getCorrelation() {
        return correlation;
    }

    /**
     * 
     * @param correlation
     *     The Correlation
     */
    @JsonProperty("Correlation")
    public void setCorrelation(com.splitsecnd.integration.atp.model.Correlation correlation) {
        this.correlation = correlation;
    }

    /**
     * 
     * @return
     *     The Device
     */
    @JsonProperty("Device")
    public com.splitsecnd.integration.atp.model.Device getDevice() {
        return device;
    }

    /**
     * 
     * @param device
     *     The Device
     */
    @JsonProperty("Device")
    public void setDevice(com.splitsecnd.integration.atp.model.Device device) {
        this.device = device;
    }

    /**
     * 
     * @return
     *     The Location
     */
    @JsonProperty("Location")
    public com.splitsecnd.integration.atp.model.Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The Location
     */
    @JsonProperty("Location")
    public void setLocation(com.splitsecnd.integration.atp.model.Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The Type
     */
    @JsonProperty("Type")
    public String getType() {
        return Type;
    }

    /**
     * 
     * @param Type
     *     The Type
     */
    @JsonProperty("Type")
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * 
     * @return
     *     The Vehicle
     */
    @JsonProperty("Vehicle")
    public com.splitsecnd.integration.atp.model.Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * 
     * @param vehicle
     *     The Vehicle
     */
    @JsonProperty("Vehicle")
    public void setVehicle(com.splitsecnd.integration.atp.model.Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Event getInstance() {
		Event e = new Event();
		e.setCaller(Caller.getInstance());
		e.setCorrelation(Correlation.getInstance());
		e.setDevice(Device.getInstance());
		e.setLocation(Location.getInstance());
		e.setVehicle(Vehicle.getInstance());
		
		return e;
	}

}
