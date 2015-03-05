
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
    "BodyType",
    "Color",
    "DeliveryDate",
    "DiagnosticInfo",
    "Energy",
    "LicencePlate",
    "Make",
    "ManufactureDate",
    "Model",
    "ModelDate",
    "Series",
    "Status",
    "Vin"
})
public class Vehicle {

    @JsonProperty("BodyType")
    private String BodyType;
    @JsonProperty("Color")
    private String Color;
    @JsonProperty("DeliveryDate")
    private String DeliveryDate;
    @JsonProperty("DiagnosticInfo")
    private com.splitsecnd.integration.atp.model.DiagnosticInfo diagnosticInfo;
    @JsonProperty("Energy")
    private String Energy;
    @JsonProperty("LicencePlate")
    private String LicencePlate;
    @JsonProperty("Make")
    private String Make;
    @JsonProperty("ManufactureDate")
    private String ManufactureDate;
    @JsonProperty("Model")
    private String Model;
    @JsonProperty("ModelDate")
    private String ModelDate;
    @JsonProperty("Series")
    private String Series;
    @JsonProperty("Status")
    private com.splitsecnd.integration.atp.model.Status status;
    @JsonProperty("Vin")
    private com.splitsecnd.integration.atp.model.Vin vin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The BodyType
     */
    @JsonProperty("BodyType")
    public String getBodyType() {
        return BodyType;
    }

    /**
     * 
     * @param BodyType
     *     The BodyType
     */
    @JsonProperty("BodyType")
    public void setBodyType(String BodyType) {
        this.BodyType = BodyType;
    }

    /**
     * 
     * @return
     *     The Color
     */
    @JsonProperty("Color")
    public String getColor() {
        return Color;
    }

    /**
     * 
     * @param Color
     *     The Color
     */
    @JsonProperty("Color")
    public void setColor(String Color) {
        this.Color = Color;
    }

    /**
     * 
     * @return
     *     The DeliveryDate
     */
    @JsonProperty("DeliveryDate")
    public String getDeliveryDate() {
        return DeliveryDate;
    }

    /**
     * 
     * @param DeliveryDate
     *     The DeliveryDate
     */
    @JsonProperty("DeliveryDate")
    public void setDeliveryDate(String DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    /**
     * 
     * @return
     *     The DiagnosticInfo
     */
    @JsonProperty("DiagnosticInfo")
    public com.splitsecnd.integration.atp.model.DiagnosticInfo getDiagnosticInfo() {
        return diagnosticInfo;
    }

    /**
     * 
     * @param diagnosticInfo
     *     The DiagnosticInfo
     */
    @JsonProperty("DiagnosticInfo")
    public void setDiagnosticInfo(com.splitsecnd.integration.atp.model.DiagnosticInfo diagnosticInfo) {
        this.diagnosticInfo = diagnosticInfo;
    }

    /**
     * 
     * @return
     *     The Energy
     */
    @JsonProperty("Energy")
    public String getEnergy() {
        return Energy;
    }

    /**
     * 
     * @param Energy
     *     The Energy
     */
    @JsonProperty("Energy")
    public void setEnergy(String Energy) {
        this.Energy = Energy;
    }

    /**
     * 
     * @return
     *     The LicencePlate
     */
    @JsonProperty("LicencePlate")
    public String getLicencePlate() {
        return LicencePlate;
    }

    /**
     * 
     * @param LicencePlate
     *     The LicencePlate
     */
    @JsonProperty("LicencePlate")
    public void setLicencePlate(String LicencePlate) {
        this.LicencePlate = LicencePlate;
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
     *     The ManufactureDate
     */
    @JsonProperty("ManufactureDate")
    public String getManufactureDate() {
        return ManufactureDate;
    }

    /**
     * 
     * @param ManufactureDate
     *     The ManufactureDate
     */
    @JsonProperty("ManufactureDate")
    public void setManufactureDate(String ManufactureDate) {
        this.ManufactureDate = ManufactureDate;
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
     *     The ModelDate
     */
    @JsonProperty("ModelDate")
    public String getModelDate() {
        return ModelDate;
    }

    /**
     * 
     * @param ModelDate
     *     The ModelDate
     */
    @JsonProperty("ModelDate")
    public void setModelDate(String ModelDate) {
        this.ModelDate = ModelDate;
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

    /**
     * 
     * @return
     *     The Status
     */
    @JsonProperty("Status")
    public com.splitsecnd.integration.atp.model.Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    @JsonProperty("Status")
    public void setStatus(com.splitsecnd.integration.atp.model.Status status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The Vin
     */
    @JsonProperty("Vin")
    public com.splitsecnd.integration.atp.model.Vin getVin() {
        return vin;
    }

    /**
     * 
     * @param vin
     *     The Vin
     */
    @JsonProperty("Vin")
    public void setVin(com.splitsecnd.integration.atp.model.Vin vin) {
        this.vin = vin;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Vehicle getInstance() {
		Vehicle v = new Vehicle();
		v.setDiagnosticInfo(DiagnosticInfo.getInstance());
		v.setStatus(Status.getInstance());
		v.setVin(Vin.getInstance());
		return v;
	}

}
