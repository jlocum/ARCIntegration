
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
    "AssistanceType",
    "BrandCode",
    "ProjectCode",
    "ServiceIdentifier"
})
public class Service {

    @JsonProperty("AssistanceType")
    private String AssistanceType;
    @JsonProperty("BrandCode")
    private String BrandCode;
    @JsonProperty("ProjectCode")
    private String ProjectCode;
    @JsonProperty("ServiceIdentifier")
    private String ServiceIdentifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The AssistanceType
     */
    @JsonProperty("AssistanceType")
    public String getAssistanceType() {
        return AssistanceType;
    }

    /**
     * 
     * @param AssistanceType
     *     The AssistanceType
     */
    @JsonProperty("AssistanceType")
    public void setAssistanceType(String AssistanceType) {
        this.AssistanceType = AssistanceType;
    }

    /**
     * 
     * @return
     *     The BrandCode
     */
    @JsonProperty("BrandCode")
    public String getBrandCode() {
        return BrandCode;
    }

    /**
     * 
     * @param BrandCode
     *     The BrandCode
     */
    @JsonProperty("BrandCode")
    public void setBrandCode(String BrandCode) {
        this.BrandCode = BrandCode;
    }

    /**
     * 
     * @return
     *     The ProjectCode
     */
    @JsonProperty("ProjectCode")
    public String getProjectCode() {
        return ProjectCode;
    }

    /**
     * 
     * @param ProjectCode
     *     The ProjectCode
     */
    @JsonProperty("ProjectCode")
    public void setProjectCode(String ProjectCode) {
        this.ProjectCode = ProjectCode;
    }

    /**
     * 
     * @return
     *     The ServiceIdentifier
     */
    @JsonProperty("ServiceIdentifier")
    public String getServiceIdentifier() {
        return ServiceIdentifier;
    }

    /**
     * 
     * @param ServiceIdentifier
     *     The ServiceIdentifier
     */
    @JsonProperty("ServiceIdentifier")
    public void setServiceIdentifier(String ServiceIdentifier) {
        this.ServiceIdentifier = ServiceIdentifier;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Service getInstance() {
		return new Service();
	}

}
