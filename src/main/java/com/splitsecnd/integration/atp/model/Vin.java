
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
    "Vds",
    "Vis",
    "Wmi"
})
public class Vin {

    @JsonProperty("Vds")
    private String Vds;
    @JsonProperty("Vis")
    private String Vis;
    @JsonProperty("Wmi")
    private String Wmi;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Vds
     */
    @JsonProperty("Vds")
    public String getVds() {
        return Vds;
    }

    /**
     * 
     * @param Vds
     *     The Vds
     */
    @JsonProperty("Vds")
    public void setVds(String Vds) {
        this.Vds = Vds;
    }

    /**
     * 
     * @return
     *     The Vis
     */
    @JsonProperty("Vis")
    public String getVis() {
        return Vis;
    }

    /**
     * 
     * @param Vis
     *     The Vis
     */
    @JsonProperty("Vis")
    public void setVis(String Vis) {
        this.Vis = Vis;
    }

    /**
     * 
     * @return
     *     The Wmi
     */
    @JsonProperty("Wmi")
    public String getWmi() {
        return Wmi;
    }

    /**
     * 
     * @param Wmi
     *     The Wmi
     */
    @JsonProperty("Wmi")
    public void setWmi(String Wmi) {
        this.Wmi = Wmi;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Vin getInstance() {
		return new Vin();
	}

	public void setVin(String vin) {
		assert vin != null;
		assert vin.length() == 17;
		
		setWmi(vin.substring(0,3));
		setVds(vin.substring(3,9));
		setVis(vin.substring(9));		
	}

}
