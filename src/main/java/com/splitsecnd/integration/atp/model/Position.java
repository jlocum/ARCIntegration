
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
    "Latitude",
    "Longitude"
})
public class Position {

    @JsonProperty("Latitude")
    private Double Latitude;
    @JsonProperty("Longitude")
    private Double Longitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Latitude
     */
    @JsonProperty("Latitude")
    public Double getLatitude() {
        return Latitude;
    }

    /**
     * 
     * @param Latitude
     *     The Latitude
     */
    @JsonProperty("Latitude")
    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * 
     * @return
     *     The Longitude
     */
    @JsonProperty("Longitude")
    public Double getLongitude() {
        return Longitude;
    }

    /**
     * 
     * @param Longitude
     *     The Longitude
     */
    @JsonProperty("Longitude")
    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Position getInstance() {
		return new Position();
	}

}
