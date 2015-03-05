
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
    "ApplicationBuild",
    "ApplicationLanguage",
    "ApplicationName",
    "ApplicationVersion",
    "HardwareVersion",
    "Id",
    "Make",
    "Model",
    "OperatingSystem",
    "OperatingSystemBuild",
    "OperatingSystemLanguage",
    "OperatingSystemVersion",
    "Series"
})
public class Device {

    @JsonProperty("ApplicationBuild")
    private String ApplicationBuild;
    @JsonProperty("ApplicationLanguage")
    private String ApplicationLanguage;
    @JsonProperty("ApplicationName")
    private String ApplicationName;
    @JsonProperty("ApplicationVersion")
    private String ApplicationVersion;
    @JsonProperty("HardwareVersion")
    private String HardwareVersion;
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("Make")
    private String Make;
    @JsonProperty("Model")
    private String Model;
    @JsonProperty("OperatingSystem")
    private String OperatingSystem;
    @JsonProperty("OperatingSystemBuild")
    private String OperatingSystemBuild;
    @JsonProperty("OperatingSystemLanguage")
    private String OperatingSystemLanguage;
    @JsonProperty("OperatingSystemVersion")
    private String OperatingSystemVersion;
    @JsonProperty("Series")
    private String Series;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ApplicationBuild
     */
    @JsonProperty("ApplicationBuild")
    public String getApplicationBuild() {
        return ApplicationBuild;
    }

    /**
     * 
     * @param ApplicationBuild
     *     The ApplicationBuild
     */
    @JsonProperty("ApplicationBuild")
    public void setApplicationBuild(String ApplicationBuild) {
        this.ApplicationBuild = ApplicationBuild;
    }

    /**
     * 
     * @return
     *     The ApplicationLanguage
     */
    @JsonProperty("ApplicationLanguage")
    public String getApplicationLanguage() {
        return ApplicationLanguage;
    }

    /**
     * 
     * @param ApplicationLanguage
     *     The ApplicationLanguage
     */
    @JsonProperty("ApplicationLanguage")
    public void setApplicationLanguage(String ApplicationLanguage) {
        this.ApplicationLanguage = ApplicationLanguage;
    }

    /**
     * 
     * @return
     *     The ApplicationName
     */
    @JsonProperty("ApplicationName")
    public String getApplicationName() {
        return ApplicationName;
    }

    /**
     * 
     * @param ApplicationName
     *     The ApplicationName
     */
    @JsonProperty("ApplicationName")
    public void setApplicationName(String ApplicationName) {
        this.ApplicationName = ApplicationName;
    }

    /**
     * 
     * @return
     *     The ApplicationVersion
     */
    @JsonProperty("ApplicationVersion")
    public String getApplicationVersion() {
        return ApplicationVersion;
    }

    /**
     * 
     * @param ApplicationVersion
     *     The ApplicationVersion
     */
    @JsonProperty("ApplicationVersion")
    public void setApplicationVersion(String ApplicationVersion) {
        this.ApplicationVersion = ApplicationVersion;
    }

    /**
     * 
     * @return
     *     The HardwareVersion
     */
    @JsonProperty("HardwareVersion")
    public String getHardwareVersion() {
        return HardwareVersion;
    }

    /**
     * 
     * @param HardwareVersion
     *     The HardwareVersion
     */
    @JsonProperty("HardwareVersion")
    public void setHardwareVersion(String HardwareVersion) {
        this.HardwareVersion = HardwareVersion;
    }

    /**
     * 
     * @return
     *     The Id
     */
    @JsonProperty("Id")
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The Id
     */
    @JsonProperty("Id")
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * 
     * @return
     *     The Make
     */
    @JsonProperty("Make")
    public String getMake() {
        return Make;
    }

    /**
     * 
     * @param Make
     *     The Make
     */
    @JsonProperty("Make")
    public void setMake(String Make) {
        this.Make = Make;
    }

    /**
     * 
     * @return
     *     The Model
     */
    @JsonProperty("Model")
    public String getModel() {
        return Model;
    }

    /**
     * 
     * @param Model
     *     The Model
     */
    @JsonProperty("Model")
    public void setModel(String Model) {
        this.Model = Model;
    }

    /**
     * 
     * @return
     *     The OperatingSystem
     */
    @JsonProperty("OperatingSystem")
    public String getOperatingSystem() {
        return OperatingSystem;
    }

    /**
     * 
     * @param OperatingSystem
     *     The OperatingSystem
     */
    @JsonProperty("OperatingSystem")
    public void setOperatingSystem(String OperatingSystem) {
        this.OperatingSystem = OperatingSystem;
    }

    /**
     * 
     * @return
     *     The OperatingSystemBuild
     */
    @JsonProperty("OperatingSystemBuild")
    public String getOperatingSystemBuild() {
        return OperatingSystemBuild;
    }

    /**
     * 
     * @param OperatingSystemBuild
     *     The OperatingSystemBuild
     */
    @JsonProperty("OperatingSystemBuild")
    public void setOperatingSystemBuild(String OperatingSystemBuild) {
        this.OperatingSystemBuild = OperatingSystemBuild;
    }

    /**
     * 
     * @return
     *     The OperatingSystemLanguage
     */
    @JsonProperty("OperatingSystemLanguage")
    public String getOperatingSystemLanguage() {
        return OperatingSystemLanguage;
    }

    /**
     * 
     * @param OperatingSystemLanguage
     *     The OperatingSystemLanguage
     */
    @JsonProperty("OperatingSystemLanguage")
    public void setOperatingSystemLanguage(String OperatingSystemLanguage) {
        this.OperatingSystemLanguage = OperatingSystemLanguage;
    }

    /**
     * 
     * @return
     *     The OperatingSystemVersion
     */
    @JsonProperty("OperatingSystemVersion")
    public String getOperatingSystemVersion() {
        return OperatingSystemVersion;
    }

    /**
     * 
     * @param OperatingSystemVersion
     *     The OperatingSystemVersion
     */
    @JsonProperty("OperatingSystemVersion")
    public void setOperatingSystemVersion(String OperatingSystemVersion) {
        this.OperatingSystemVersion = OperatingSystemVersion;
    }

    /**
     * 
     * @return
     *     The Series
     */
    @JsonProperty("Series")
    public String getSeries() {
        return Series;
    }

    /**
     * 
     * @param Series
     *     The Series
     */
    @JsonProperty("Series")
    public void setSeries(String Series) {
        this.Series = Series;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Device getInstance() {
		return new Device();
	}
}
