
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
    "CustomerCreationTimestamp",
    "CustomerEventId",
    "CustomerLastUpdateTimestamp"
})
public class Correlation {

    @JsonProperty("CustomerCreationTimestamp")
    private String CustomerCreationTimestamp;
    @JsonProperty("CustomerEventId")
    private String CustomerEventId;
    @JsonProperty("CustomerLastUpdateTimestamp")
    private String CustomerLastUpdateTimestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The CustomerCreationTimestamp
     */
    @JsonProperty("CustomerCreationTimestamp")
    public String getCustomerCreationTimestamp() {
        return CustomerCreationTimestamp;
    }

    /**
     * 
     * @param CustomerCreationTimestamp
     *     The CustomerCreationTimestamp
     */
    @JsonProperty("CustomerCreationTimestamp")
    public void setCustomerCreationTimestamp(String CustomerCreationTimestamp) {
        this.CustomerCreationTimestamp = CustomerCreationTimestamp;
    }

    /**
     * 
     * @return
     *     The CustomerEventId
     */
    @JsonProperty("CustomerEventId")
    public String getCustomerEventId() {
        return CustomerEventId;
    }

    /**
     * 
     * @param CustomerEventId
     *     The CustomerEventId
     */
    @JsonProperty("CustomerEventId")
    public void setCustomerEventId(String CustomerEventId) {
        this.CustomerEventId = CustomerEventId;
    }

    /**
     * 
     * @return
     *     The CustomerLastUpdateTimestamp
     */
    @JsonProperty("CustomerLastUpdateTimestamp")
    public String getCustomerLastUpdateTimestamp() {
        return CustomerLastUpdateTimestamp;
    }

    /**
     * 
     * @param CustomerLastUpdateTimestamp
     *     The CustomerLastUpdateTimestamp
     */
    @JsonProperty("CustomerLastUpdateTimestamp")
    public void setCustomerLastUpdateTimestamp(String CustomerLastUpdateTimestamp) {
        this.CustomerLastUpdateTimestamp = CustomerLastUpdateTimestamp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Correlation getInstance() {
		return new Correlation();
	}

}
