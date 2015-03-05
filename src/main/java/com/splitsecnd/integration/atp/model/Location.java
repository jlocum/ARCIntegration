
package com.splitsecnd.integration.atp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "Altitude",
    "AltitudeAccuracy",
    "CreationTimestamp",
    "Heading",
    "HeadingAccuracy",
    "IsPositionTrustable",
    "NumberOfSatellites",
    "Position",
    "PositionAccuracy",
    "PositionTraces",
    "Speed",
    "Srid"
})
public class Location {

    @JsonProperty("Altitude")
    private Long Altitude;
    @JsonProperty("AltitudeAccuracy")
    private Double AltitudeAccuracy;
    @JsonProperty("CreationTimestamp")
    private String CreationTimestamp;
    @JsonProperty("Heading")
    private Integer Heading;
    @JsonProperty("HeadingAccuracy")
    private Double HeadingAccuracy;
    @JsonProperty("IsPositionTrustable")
    private Boolean IsPositionTrustable;
    @JsonProperty("NumberOfSatellites")
    private Integer NumberOfSatellites;
    @JsonProperty("Position")
    private com.splitsecnd.integration.atp.model.Position position;
    @JsonProperty("PositionAccuracy")
    private Double PositionAccuracy;
    @JsonProperty("PositionTraces")
    private List<PositionTrace> positionTraces = new ArrayList<PositionTrace>();
    @JsonProperty("Speed")
    private Integer Speed;
    @JsonProperty("Srid")
    private Integer Srid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Altitude
     */
    @JsonProperty("Altitude")
    public Long getAltitude() {
        return Altitude;
    }

    /**
     * 
     * @param Altitude
     *     The Altitude
     */
    @JsonProperty("Altitude")
    public void setAltitude(Long Altitude) {
        this.Altitude = Altitude;
    }

    /**
     * 
     * @return
     *     The AltitudeAccuracy
     */
    @JsonProperty("AltitudeAccuracy")
    public Double getAltitudeAccuracy() {
        return AltitudeAccuracy;
    }

    /**
     * 
     * @param AltitudeAccuracy
     *     The AltitudeAccuracy
     */
    @JsonProperty("AltitudeAccuracy")
    public void setAltitudeAccuracy(Double AltitudeAccuracy) {
        this.AltitudeAccuracy = AltitudeAccuracy;
    }

    /**
     * 
     * @return
     *     The CreationTimestamp
     */
    @JsonProperty("CreationTimestamp")
    public String getCreationTimestamp() {
        return CreationTimestamp;
    }

    /**
     * 
     * @param CreationTimestamp
     *     The CreationTimestamp
     */
    @JsonProperty("CreationTimestamp")
    public void setCreationTimestamp(String CreationTimestamp) {
        this.CreationTimestamp = CreationTimestamp;
    }

    /**
     * 
     * @return
     *     The Heading
     */
    @JsonProperty("Heading")
    public Integer getHeading() {
        return Heading;
    }

    /**
     * 
     * @param Heading
     *     The Heading
     */
    @JsonProperty("Heading")
    public void setHeading(Integer Heading) {
        this.Heading = Heading;
    }

    /**
     * 
     * @return
     *     The HeadingAccuracy
     */
    @JsonProperty("HeadingAccuracy")
    public Double getHeadingAccuracy() {
        return HeadingAccuracy;
    }

    /**
     * 
     * @param HeadingAccuracy
     *     The HeadingAccuracy
     */
    @JsonProperty("HeadingAccuracy")
    public void setHeadingAccuracy(Double HeadingAccuracy) {
        this.HeadingAccuracy = HeadingAccuracy;
    }

    /**
     * 
     * @return
     *     The IsPositionTrustable
     */
    @JsonProperty("IsPositionTrustable")
    public Boolean getIsPositionTrustable() {
        return IsPositionTrustable;
    }

    /**
     * 
     * @param IsPositionTrustable
     *     The IsPositionTrustable
     */
    @JsonProperty("IsPositionTrustable")
    public void setIsPositionTrustable(Boolean IsPositionTrustable) {
        this.IsPositionTrustable = IsPositionTrustable;
    }

    /**
     * 
     * @return
     *     The NumberOfSatellites
     */
    @JsonProperty("NumberOfSatellites")
    public Integer getNumberOfSatellites() {
        return NumberOfSatellites;
    }

    /**
     * 
     * @param NumberOfSatellites
     *     The NumberOfSatellites
     */
    @JsonProperty("NumberOfSatellites")
    public void setNumberOfSatellites(Integer NumberOfSatellites) {
        this.NumberOfSatellites = NumberOfSatellites;
    }

    /**
     * 
     * @return
     *     The Position
     */
    @JsonProperty("Position")
    public com.splitsecnd.integration.atp.model.Position getPosition() {
        return position;
    }

    /**
     * 
     * @param position
     *     The Position
     */
    @JsonProperty("Position")
    public void setPosition(com.splitsecnd.integration.atp.model.Position position) {
        this.position = position;
    }

    /**
     * 
     * @return
     *     The PositionAccuracy
     */
    @JsonProperty("PositionAccuracy")
    public Double getPositionAccuracy() {
        return PositionAccuracy;
    }

    /**
     * 
     * @param PositionAccuracy
     *     The PositionAccuracy
     */
    @JsonProperty("PositionAccuracy")
    public void setPositionAccuracy(Double PositionAccuracy) {
        this.PositionAccuracy = PositionAccuracy;
    }

    /**
     * 
     * @return
     *     The PositionTraces
     */
    @JsonProperty("PositionTraces")
    public List<PositionTrace> getPositionTraces() {
        return positionTraces;
    }

    /**
     * 
     * @param positionTraces
     *     The PositionTraces
     */
    @JsonProperty("PositionTraces")
    public void setPositionTraces(List<PositionTrace> positionTraces) {
        this.positionTraces = positionTraces;
    }

    /**
     * 
     * @return
     *     The Speed
     */
    @JsonProperty("Speed")
    public Integer getSpeed() {
        return Speed;
    }

    /**
     * 
     * @param Speed
     *     The Speed
     */
    @JsonProperty("Speed")
    public void setSpeed(Integer Speed) {
        this.Speed = Speed;
    }

    /**
     * 
     * @return
     *     The Srid
     */
    @JsonProperty("Srid")
    public Integer getSrid() {
        return Srid;
    }

    /**
     * 
     * @param Srid
     *     The Srid
     */
    @JsonProperty("Srid")
    public void setSrid(Integer Srid) {
        this.Srid = Srid;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Location getInstance() {
		Location l = new Location();
		l.setPosition(Position.getInstance());
		l.setPositionTraces(new ArrayList<PositionTrace>());
		return l;
	}

}
