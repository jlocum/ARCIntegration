
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
    "AirbagTriggered",
    "CrankInhibitionActivated",
    "Crashed",
    "Deceleration",
    "DriverSideCrash",
    "EngineOn",
    "FrontCrash",
    "IgnitionOn",
    "InCarAlarmActivated",
    "Moving",
    "NumberOfFastenSeatBelts",
    "NumberOfOccupiedSeats",
    "NumberOfTriggeredAirbags",
    "PassengerSideCrash",
    "RearCrash",
    "Rolled",
    "SideCrash",
    "TheftTrackingActivated",
    "TowTruckNeed"
})
public class Status {

    @JsonProperty("AirbagTriggered")
    private Boolean AirbagTriggered;
    @JsonProperty("CrankInhibitionActivated")
    private Boolean CrankInhibitionActivated;
    @JsonProperty("Crashed")
    private Boolean Crashed;
    @JsonProperty("Deceleration")
    private Integer Deceleration;
    @JsonProperty("DriverSideCrash")
    private Boolean DriverSideCrash;
    @JsonProperty("EngineOn")
    private Boolean EngineOn;
    @JsonProperty("FrontCrash")
    private Boolean FrontCrash;
    @JsonProperty("IgnitionOn")
    private Boolean IgnitionOn;
    @JsonProperty("InCarAlarmActivated")
    private Boolean InCarAlarmActivated;
    @JsonProperty("Moving")
    private Boolean Moving;
    @JsonProperty("NumberOfFastenSeatBelts")
    private Integer NumberOfFastenSeatBelts;
    @JsonProperty("NumberOfOccupiedSeats")
    private Integer NumberOfOccupiedSeats;
    @JsonProperty("NumberOfTriggeredAirbags")
    private Integer NumberOfTriggeredAirbags;
    @JsonProperty("PassengerSideCrash")
    private Boolean PassengerSideCrash;
    @JsonProperty("RearCrash")
    private Boolean RearCrash;
    @JsonProperty("Rolled")
    private Boolean Rolled;
    @JsonProperty("SideCrash")
    private Boolean SideCrash;
    @JsonProperty("TheftTrackingActivated")
    private Boolean TheftTrackingActivated;
    @JsonProperty("TowTruckNeed")
    private Boolean TowTruckNeed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The AirbagTriggered
     */
    @JsonProperty("AirbagTriggered")
    public Boolean getAirbagTriggered() {
        return AirbagTriggered;
    }

    /**
     * 
     * @param AirbagTriggered
     *     The AirbagTriggered
     */
    @JsonProperty("AirbagTriggered")
    public void setAirbagTriggered(Boolean AirbagTriggered) {
        this.AirbagTriggered = AirbagTriggered;
    }

    /**
     * 
     * @return
     *     The CrankInhibitionActivated
     */
    @JsonProperty("CrankInhibitionActivated")
    public Boolean getCrankInhibitionActivated() {
        return CrankInhibitionActivated;
    }

    /**
     * 
     * @param CrankInhibitionActivated
     *     The CrankInhibitionActivated
     */
    @JsonProperty("CrankInhibitionActivated")
    public void setCrankInhibitionActivated(Boolean CrankInhibitionActivated) {
        this.CrankInhibitionActivated = CrankInhibitionActivated;
    }

    /**
     * 
     * @return
     *     The Crashed
     */
    @JsonProperty("Crashed")
    public Boolean getCrashed() {
        return Crashed;
    }

    /**
     * 
     * @param Crashed
     *     The Crashed
     */
    @JsonProperty("Crashed")
    public void setCrashed(Boolean Crashed) {
        this.Crashed = Crashed;
    }

    /**
     * 
     * @return
     *     The Deceleration
     */
    @JsonProperty("Deceleration")
    public Integer getDeceleration() {
        return Deceleration;
    }

    /**
     * 
     * @param Deceleration
     *     The Deceleration
     */
    @JsonProperty("Deceleration")
    public void setDeceleration(Integer Deceleration) {
        this.Deceleration = Deceleration;
    }

    /**
     * 
     * @return
     *     The DriverSideCrash
     */
    @JsonProperty("DriverSideCrash")
    public Boolean getDriverSideCrash() {
        return DriverSideCrash;
    }

    /**
     * 
     * @param DriverSideCrash
     *     The DriverSideCrash
     */
    @JsonProperty("DriverSideCrash")
    public void setDriverSideCrash(Boolean DriverSideCrash) {
        this.DriverSideCrash = DriverSideCrash;
    }

    /**
     * 
     * @return
     *     The EngineOn
     */
    @JsonProperty("EngineOn")
    public Boolean getEngineOn() {
        return EngineOn;
    }

    /**
     * 
     * @param EngineOn
     *     The EngineOn
     */
    @JsonProperty("EngineOn")
    public void setEngineOn(Boolean EngineOn) {
        this.EngineOn = EngineOn;
    }

    /**
     * 
     * @return
     *     The FrontCrash
     */
    @JsonProperty("FrontCrash")
    public Boolean getFrontCrash() {
        return FrontCrash;
    }

    /**
     * 
     * @param FrontCrash
     *     The FrontCrash
     */
    @JsonProperty("FrontCrash")
    public void setFrontCrash(Boolean FrontCrash) {
        this.FrontCrash = FrontCrash;
    }

    /**
     * 
     * @return
     *     The IgnitionOn
     */
    @JsonProperty("IgnitionOn")
    public Boolean getIgnitionOn() {
        return IgnitionOn;
    }

    /**
     * 
     * @param IgnitionOn
     *     The IgnitionOn
     */
    @JsonProperty("IgnitionOn")
    public void setIgnitionOn(Boolean IgnitionOn) {
        this.IgnitionOn = IgnitionOn;
    }

    /**
     * 
     * @return
     *     The InCarAlarmActivated
     */
    @JsonProperty("InCarAlarmActivated")
    public Boolean getInCarAlarmActivated() {
        return InCarAlarmActivated;
    }

    /**
     * 
     * @param InCarAlarmActivated
     *     The InCarAlarmActivated
     */
    @JsonProperty("InCarAlarmActivated")
    public void setInCarAlarmActivated(Boolean InCarAlarmActivated) {
        this.InCarAlarmActivated = InCarAlarmActivated;
    }

    /**
     * 
     * @return
     *     The Moving
     */
    @JsonProperty("Moving")
    public Boolean getMoving() {
        return Moving;
    }

    /**
     * 
     * @param Moving
     *     The Moving
     */
    @JsonProperty("Moving")
    public void setMoving(Boolean Moving) {
        this.Moving = Moving;
    }

    /**
     * 
     * @return
     *     The NumberOfFastenSeatBelts
     */
    @JsonProperty("NumberOfFastenSeatBelts")
    public Integer getNumberOfFastenSeatBelts() {
        return NumberOfFastenSeatBelts;
    }

    /**
     * 
     * @param NumberOfFastenSeatBelts
     *     The NumberOfFastenSeatBelts
     */
    @JsonProperty("NumberOfFastenSeatBelts")
    public void setNumberOfFastenSeatBelts(Integer NumberOfFastenSeatBelts) {
        this.NumberOfFastenSeatBelts = NumberOfFastenSeatBelts;
    }

    /**
     * 
     * @return
     *     The NumberOfOccupiedSeats
     */
    @JsonProperty("NumberOfOccupiedSeats")
    public Integer getNumberOfOccupiedSeats() {
        return NumberOfOccupiedSeats;
    }

    /**
     * 
     * @param NumberOfOccupiedSeats
     *     The NumberOfOccupiedSeats
     */
    @JsonProperty("NumberOfOccupiedSeats")
    public void setNumberOfOccupiedSeats(Integer NumberOfOccupiedSeats) {
        this.NumberOfOccupiedSeats = NumberOfOccupiedSeats;
    }

    /**
     * 
     * @return
     *     The NumberOfTriggeredAirbags
     */
    @JsonProperty("NumberOfTriggeredAirbags")
    public Integer getNumberOfTriggeredAirbags() {
        return NumberOfTriggeredAirbags;
    }

    /**
     * 
     * @param NumberOfTriggeredAirbags
     *     The NumberOfTriggeredAirbags
     */
    @JsonProperty("NumberOfTriggeredAirbags")
    public void setNumberOfTriggeredAirbags(Integer NumberOfTriggeredAirbags) {
        this.NumberOfTriggeredAirbags = NumberOfTriggeredAirbags;
    }

    /**
     * 
     * @return
     *     The PassengerSideCrash
     */
    @JsonProperty("PassengerSideCrash")
    public Boolean getPassengerSideCrash() {
        return PassengerSideCrash;
    }

    /**
     * 
     * @param PassengerSideCrash
     *     The PassengerSideCrash
     */
    @JsonProperty("PassengerSideCrash")
    public void setPassengerSideCrash(Boolean PassengerSideCrash) {
        this.PassengerSideCrash = PassengerSideCrash;
    }

    /**
     * 
     * @return
     *     The RearCrash
     */
    @JsonProperty("RearCrash")
    public Boolean getRearCrash() {
        return RearCrash;
    }

    /**
     * 
     * @param RearCrash
     *     The RearCrash
     */
    @JsonProperty("RearCrash")
    public void setRearCrash(Boolean RearCrash) {
        this.RearCrash = RearCrash;
    }

    /**
     * 
     * @return
     *     The Rolled
     */
    @JsonProperty("Rolled")
    public Boolean getRolled() {
        return Rolled;
    }

    /**
     * 
     * @param Rolled
     *     The Rolled
     */
    @JsonProperty("Rolled")
    public void setRolled(Boolean Rolled) {
        this.Rolled = Rolled;
    }

    /**
     * 
     * @return
     *     The SideCrash
     */
    @JsonProperty("SideCrash")
    public Boolean getSideCrash() {
        return SideCrash;
    }

    /**
     * 
     * @param SideCrash
     *     The SideCrash
     */
    @JsonProperty("SideCrash")
    public void setSideCrash(Boolean SideCrash) {
        this.SideCrash = SideCrash;
    }

    /**
     * 
     * @return
     *     The TheftTrackingActivated
     */
    @JsonProperty("TheftTrackingActivated")
    public Boolean getTheftTrackingActivated() {
        return TheftTrackingActivated;
    }

    /**
     * 
     * @param TheftTrackingActivated
     *     The TheftTrackingActivated
     */
    @JsonProperty("TheftTrackingActivated")
    public void setTheftTrackingActivated(Boolean TheftTrackingActivated) {
        this.TheftTrackingActivated = TheftTrackingActivated;
    }

    /**
     * 
     * @return
     *     The TowTruckNeed
     */
    @JsonProperty("TowTruckNeed")
    public Boolean getTowTruckNeed() {
        return TowTruckNeed;
    }

    /**
     * 
     * @param TowTruckNeed
     *     The TowTruckNeed
     */
    @JsonProperty("TowTruckNeed")
    public void setTowTruckNeed(Boolean TowTruckNeed) {
        this.TowTruckNeed = TowTruckNeed;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static Status getInstance() {
		return new Status();
	}

}
