
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
    "AbsAlarm",
    "AirbagAlarm",
    "AntiTheftAlarm",
    "BatteryAlarm",
    "BatteryChargingPercentage",
    "BrakeAlarm",
    "BrakeLevelAlarm",
    "BrakePadAlarm",
    "CrashAlarm",
    "EngineAlarm",
    "EobdAlarm",
    "EspAlarm",
    "FluidPressureAlarm",
    "FpsFluid2Alarm",
    "FpsFluidLevelAlarm",
    "FpsFullAlarm",
    "FuelEconomy",
    "FuelLevelPercentage",
    "FuelRange",
    "GearOilTempAlarm",
    "GearboxAlarm",
    "Kilometers",
    "LpgLevelPercentage",
    "LpgRange",
    "OilLevel",
    "OilLevelAlarm",
    "OilPressureAlarm",
    "OilTemperature",
    "OilTemperatureAlarm",
    "ParkBrakeAlarm",
    "ServiceDays",
    "ServiceRange",
    "StarterStatus",
    "Steering2Alarm",
    "SteeringAlarm",
    "Suspension2Alarm",
    "Suspension3Alarm",
    "SuspensionMajorAlarm",
    "SuspensionSpeedAlarm",
    "TireFrontLeftAlarm",
    "TireFrontLeftPressure",
    "TireFrontRightAlarm",
    "TireFrontRightPressure",
    "TirePressureAlarm",
    "TireRearLeftAlarm",
    "TireRearLeftPressure",
    "TireRearRightAlarm",
    "TireRearRightPressure",
    "WaterInFuelAlarm",
    "WaterLevelAlarm",
    "WaterTemperature",
    "WaterTemperatureAlarm"
})
public class DiagnosticInfo {

    @JsonProperty("AbsAlarm")
    private Boolean AbsAlarm;
    @JsonProperty("AirbagAlarm")
    private Boolean AirbagAlarm;
    @JsonProperty("AntiTheftAlarm")
    private Boolean AntiTheftAlarm;
    @JsonProperty("BatteryAlarm")
    private Boolean BatteryAlarm;
    @JsonProperty("BatteryChargingPercentage")
    private Integer BatteryChargingPercentage;
    @JsonProperty("BrakeAlarm")
    private Boolean BrakeAlarm;
    @JsonProperty("BrakeLevelAlarm")
    private Boolean BrakeLevelAlarm;
    @JsonProperty("BrakePadAlarm")
    private Boolean BrakePadAlarm;
    @JsonProperty("CrashAlarm")
    private Boolean CrashAlarm;
    @JsonProperty("EngineAlarm")
    private Boolean EngineAlarm;
    @JsonProperty("EobdAlarm")
    private Boolean EobdAlarm;
    @JsonProperty("EspAlarm")
    private Boolean EspAlarm;
    @JsonProperty("FluidPressureAlarm")
    private Boolean FluidPressureAlarm;
    @JsonProperty("FpsFluid2Alarm")
    private Boolean FpsFluid2Alarm;
    @JsonProperty("FpsFluidLevelAlarm")
    private Boolean FpsFluidLevelAlarm;
    @JsonProperty("FpsFullAlarm")
    private Boolean FpsFullAlarm;
    @JsonProperty("FuelEconomy")
    private Integer FuelEconomy;
    @JsonProperty("FuelLevelPercentage")
    private Integer FuelLevelPercentage;
    @JsonProperty("FuelRange")
    private Integer FuelRange;
    @JsonProperty("GearOilTempAlarm")
    private Boolean GearOilTempAlarm;
    @JsonProperty("GearboxAlarm")
    private Boolean GearboxAlarm;
    @JsonProperty("Kilometers")
    private Integer Kilometers;
    @JsonProperty("LpgLevelPercentage")
    private Integer LpgLevelPercentage;
    @JsonProperty("LpgRange")
    private Integer LpgRange;
    @JsonProperty("OilLevel")
    private Integer OilLevel;
    @JsonProperty("OilLevelAlarm")
    private Boolean OilLevelAlarm;
    @JsonProperty("OilPressureAlarm")
    private Boolean OilPressureAlarm;
    @JsonProperty("OilTemperature")
    private Integer OilTemperature;
    @JsonProperty("OilTemperatureAlarm")
    private Boolean OilTemperatureAlarm;
    @JsonProperty("ParkBrakeAlarm")
    private Boolean ParkBrakeAlarm;
    @JsonProperty("ServiceDays")
    private Integer ServiceDays;
    @JsonProperty("ServiceRange")
    private Integer ServiceRange;
    @JsonProperty("StarterStatus")
    private String StarterStatus;
    @JsonProperty("Steering2Alarm")
    private Boolean Steering2Alarm;
    @JsonProperty("SteeringAlarm")
    private Boolean SteeringAlarm;
    @JsonProperty("Suspension2Alarm")
    private Boolean Suspension2Alarm;
    @JsonProperty("Suspension3Alarm")
    private Boolean Suspension3Alarm;
    @JsonProperty("SuspensionMajorAlarm")
    private Boolean SuspensionMajorAlarm;
    @JsonProperty("SuspensionSpeedAlarm")
    private Boolean SuspensionSpeedAlarm;
    @JsonProperty("TireFrontLeftAlarm")
    private Boolean TireFrontLeftAlarm;
    @JsonProperty("TireFrontLeftPressure")
    private Double TireFrontLeftPressure;
    @JsonProperty("TireFrontRightAlarm")
    private Boolean TireFrontRightAlarm;
    @JsonProperty("TireFrontRightPressure")
    private Double TireFrontRightPressure;
    @JsonProperty("TirePressureAlarm")
    private Boolean TirePressureAlarm;
    @JsonProperty("TireRearLeftAlarm")
    private Boolean TireRearLeftAlarm;
    @JsonProperty("TireRearLeftPressure")
    private Double TireRearLeftPressure;
    @JsonProperty("TireRearRightAlarm")
    private Boolean TireRearRightAlarm;
    @JsonProperty("TireRearRightPressure")
    private Double TireRearRightPressure;
    @JsonProperty("WaterInFuelAlarm")
    private Boolean WaterInFuelAlarm;
    @JsonProperty("WaterLevelAlarm")
    private Boolean WaterLevelAlarm;
    @JsonProperty("WaterTemperature")
    private Integer WaterTemperature;
    @JsonProperty("WaterTemperatureAlarm")
    private Boolean WaterTemperatureAlarm;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The AbsAlarm
     */
    @JsonProperty("AbsAlarm")
    public Boolean getAbsAlarm() {
        return AbsAlarm;
    }

    /**
     * 
     * @param AbsAlarm
     *     The AbsAlarm
     */
    @JsonProperty("AbsAlarm")
    public void setAbsAlarm(Boolean AbsAlarm) {
        this.AbsAlarm = AbsAlarm;
    }

    /**
     * 
     * @return
     *     The AirbagAlarm
     */
    @JsonProperty("AirbagAlarm")
    public Boolean getAirbagAlarm() {
        return AirbagAlarm;
    }

    /**
     * 
     * @param AirbagAlarm
     *     The AirbagAlarm
     */
    @JsonProperty("AirbagAlarm")
    public void setAirbagAlarm(Boolean AirbagAlarm) {
        this.AirbagAlarm = AirbagAlarm;
    }

    /**
     * 
     * @return
     *     The AntiTheftAlarm
     */
    @JsonProperty("AntiTheftAlarm")
    public Boolean getAntiTheftAlarm() {
        return AntiTheftAlarm;
    }

    /**
     * 
     * @param AntiTheftAlarm
     *     The AntiTheftAlarm
     */
    @JsonProperty("AntiTheftAlarm")
    public void setAntiTheftAlarm(Boolean AntiTheftAlarm) {
        this.AntiTheftAlarm = AntiTheftAlarm;
    }

    /**
     * 
     * @return
     *     The BatteryAlarm
     */
    @JsonProperty("BatteryAlarm")
    public Boolean getBatteryAlarm() {
        return BatteryAlarm;
    }

    /**
     * 
     * @param BatteryAlarm
     *     The BatteryAlarm
     */
    @JsonProperty("BatteryAlarm")
    public void setBatteryAlarm(Boolean BatteryAlarm) {
        this.BatteryAlarm = BatteryAlarm;
    }

    /**
     * 
     * @return
     *     The BatteryChargingPercentage
     */
    @JsonProperty("BatteryChargingPercentage")
    public Integer getBatteryChargingPercentage() {
        return BatteryChargingPercentage;
    }

    /**
     * 
     * @param BatteryChargingPercentage
     *     The BatteryChargingPercentage
     */
    @JsonProperty("BatteryChargingPercentage")
    public void setBatteryChargingPercentage(Integer BatteryChargingPercentage) {
        this.BatteryChargingPercentage = BatteryChargingPercentage;
    }

    /**
     * 
     * @return
     *     The BrakeAlarm
     */
    @JsonProperty("BrakeAlarm")
    public Boolean getBrakeAlarm() {
        return BrakeAlarm;
    }

    /**
     * 
     * @param BrakeAlarm
     *     The BrakeAlarm
     */
    @JsonProperty("BrakeAlarm")
    public void setBrakeAlarm(Boolean BrakeAlarm) {
        this.BrakeAlarm = BrakeAlarm;
    }

    /**
     * 
     * @return
     *     The BrakeLevelAlarm
     */
    @JsonProperty("BrakeLevelAlarm")
    public Boolean getBrakeLevelAlarm() {
        return BrakeLevelAlarm;
    }

    /**
     * 
     * @param BrakeLevelAlarm
     *     The BrakeLevelAlarm
     */
    @JsonProperty("BrakeLevelAlarm")
    public void setBrakeLevelAlarm(Boolean BrakeLevelAlarm) {
        this.BrakeLevelAlarm = BrakeLevelAlarm;
    }

    /**
     * 
     * @return
     *     The BrakePadAlarm
     */
    @JsonProperty("BrakePadAlarm")
    public Boolean getBrakePadAlarm() {
        return BrakePadAlarm;
    }

    /**
     * 
     * @param BrakePadAlarm
     *     The BrakePadAlarm
     */
    @JsonProperty("BrakePadAlarm")
    public void setBrakePadAlarm(Boolean BrakePadAlarm) {
        this.BrakePadAlarm = BrakePadAlarm;
    }

    /**
     * 
     * @return
     *     The CrashAlarm
     */
    @JsonProperty("CrashAlarm")
    public Boolean getCrashAlarm() {
        return CrashAlarm;
    }

    /**
     * 
     * @param CrashAlarm
     *     The CrashAlarm
     */
    @JsonProperty("CrashAlarm")
    public void setCrashAlarm(Boolean CrashAlarm) {
        this.CrashAlarm = CrashAlarm;
    }

    /**
     * 
     * @return
     *     The EngineAlarm
     */
    @JsonProperty("EngineAlarm")
    public Boolean getEngineAlarm() {
        return EngineAlarm;
    }

    /**
     * 
     * @param EngineAlarm
     *     The EngineAlarm
     */
    @JsonProperty("EngineAlarm")
    public void setEngineAlarm(Boolean EngineAlarm) {
        this.EngineAlarm = EngineAlarm;
    }

    /**
     * 
     * @return
     *     The EobdAlarm
     */
    @JsonProperty("EobdAlarm")
    public Boolean getEobdAlarm() {
        return EobdAlarm;
    }

    /**
     * 
     * @param EobdAlarm
     *     The EobdAlarm
     */
    @JsonProperty("EobdAlarm")
    public void setEobdAlarm(Boolean EobdAlarm) {
        this.EobdAlarm = EobdAlarm;
    }

    /**
     * 
     * @return
     *     The EspAlarm
     */
    @JsonProperty("EspAlarm")
    public Boolean getEspAlarm() {
        return EspAlarm;
    }

    /**
     * 
     * @param EspAlarm
     *     The EspAlarm
     */
    @JsonProperty("EspAlarm")
    public void setEspAlarm(Boolean EspAlarm) {
        this.EspAlarm = EspAlarm;
    }

    /**
     * 
     * @return
     *     The FluidPressureAlarm
     */
    @JsonProperty("FluidPressureAlarm")
    public Boolean getFluidPressureAlarm() {
        return FluidPressureAlarm;
    }

    /**
     * 
     * @param FluidPressureAlarm
     *     The FluidPressureAlarm
     */
    @JsonProperty("FluidPressureAlarm")
    public void setFluidPressureAlarm(Boolean FluidPressureAlarm) {
        this.FluidPressureAlarm = FluidPressureAlarm;
    }

    /**
     * 
     * @return
     *     The FpsFluid2Alarm
     */
    @JsonProperty("FpsFluid2Alarm")
    public Boolean getFpsFluid2Alarm() {
        return FpsFluid2Alarm;
    }

    /**
     * 
     * @param FpsFluid2Alarm
     *     The FpsFluid2Alarm
     */
    @JsonProperty("FpsFluid2Alarm")
    public void setFpsFluid2Alarm(Boolean FpsFluid2Alarm) {
        this.FpsFluid2Alarm = FpsFluid2Alarm;
    }

    /**
     * 
     * @return
     *     The FpsFluidLevelAlarm
     */
    @JsonProperty("FpsFluidLevelAlarm")
    public Boolean getFpsFluidLevelAlarm() {
        return FpsFluidLevelAlarm;
    }

    /**
     * 
     * @param FpsFluidLevelAlarm
     *     The FpsFluidLevelAlarm
     */
    @JsonProperty("FpsFluidLevelAlarm")
    public void setFpsFluidLevelAlarm(Boolean FpsFluidLevelAlarm) {
        this.FpsFluidLevelAlarm = FpsFluidLevelAlarm;
    }

    /**
     * 
     * @return
     *     The FpsFullAlarm
     */
    @JsonProperty("FpsFullAlarm")
    public Boolean getFpsFullAlarm() {
        return FpsFullAlarm;
    }

    /**
     * 
     * @param FpsFullAlarm
     *     The FpsFullAlarm
     */
    @JsonProperty("FpsFullAlarm")
    public void setFpsFullAlarm(Boolean FpsFullAlarm) {
        this.FpsFullAlarm = FpsFullAlarm;
    }

    /**
     * 
     * @return
     *     The FuelEconomy
     */
    @JsonProperty("FuelEconomy")
    public Integer getFuelEconomy() {
        return FuelEconomy;
    }

    /**
     * 
     * @param FuelEconomy
     *     The FuelEconomy
     */
    @JsonProperty("FuelEconomy")
    public void setFuelEconomy(Integer FuelEconomy) {
        this.FuelEconomy = FuelEconomy;
    }

    /**
     * 
     * @return
     *     The FuelLevelPercentage
     */
    @JsonProperty("FuelLevelPercentage")
    public Integer getFuelLevelPercentage() {
        return FuelLevelPercentage;
    }

    /**
     * 
     * @param FuelLevelPercentage
     *     The FuelLevelPercentage
     */
    @JsonProperty("FuelLevelPercentage")
    public void setFuelLevelPercentage(Integer FuelLevelPercentage) {
        this.FuelLevelPercentage = FuelLevelPercentage;
    }

    /**
     * 
     * @return
     *     The FuelRange
     */
    @JsonProperty("FuelRange")
    public Integer getFuelRange() {
        return FuelRange;
    }

    /**
     * 
     * @param FuelRange
     *     The FuelRange
     */
    @JsonProperty("FuelRange")
    public void setFuelRange(Integer FuelRange) {
        this.FuelRange = FuelRange;
    }

    /**
     * 
     * @return
     *     The GearOilTempAlarm
     */
    @JsonProperty("GearOilTempAlarm")
    public Boolean getGearOilTempAlarm() {
        return GearOilTempAlarm;
    }

    /**
     * 
     * @param GearOilTempAlarm
     *     The GearOilTempAlarm
     */
    @JsonProperty("GearOilTempAlarm")
    public void setGearOilTempAlarm(Boolean GearOilTempAlarm) {
        this.GearOilTempAlarm = GearOilTempAlarm;
    }

    /**
     * 
     * @return
     *     The GearboxAlarm
     */
    @JsonProperty("GearboxAlarm")
    public Boolean getGearboxAlarm() {
        return GearboxAlarm;
    }

    /**
     * 
     * @param GearboxAlarm
     *     The GearboxAlarm
     */
    @JsonProperty("GearboxAlarm")
    public void setGearboxAlarm(Boolean GearboxAlarm) {
        this.GearboxAlarm = GearboxAlarm;
    }

    /**
     * 
     * @return
     *     The Kilometers
     */
    @JsonProperty("Kilometers")
    public Integer getKilometers() {
        return Kilometers;
    }

    /**
     * 
     * @param Kilometers
     *     The Kilometers
     */
    @JsonProperty("Kilometers")
    public void setKilometers(Integer Kilometers) {
        this.Kilometers = Kilometers;
    }

    /**
     * 
     * @return
     *     The LpgLevelPercentage
     */
    @JsonProperty("LpgLevelPercentage")
    public Integer getLpgLevelPercentage() {
        return LpgLevelPercentage;
    }

    /**
     * 
     * @param LpgLevelPercentage
     *     The LpgLevelPercentage
     */
    @JsonProperty("LpgLevelPercentage")
    public void setLpgLevelPercentage(Integer LpgLevelPercentage) {
        this.LpgLevelPercentage = LpgLevelPercentage;
    }

    /**
     * 
     * @return
     *     The LpgRange
     */
    @JsonProperty("LpgRange")
    public Integer getLpgRange() {
        return LpgRange;
    }

    /**
     * 
     * @param LpgRange
     *     The LpgRange
     */
    @JsonProperty("LpgRange")
    public void setLpgRange(Integer LpgRange) {
        this.LpgRange = LpgRange;
    }

    /**
     * 
     * @return
     *     The OilLevel
     */
    @JsonProperty("OilLevel")
    public Integer getOilLevel() {
        return OilLevel;
    }

    /**
     * 
     * @param OilLevel
     *     The OilLevel
     */
    @JsonProperty("OilLevel")
    public void setOilLevel(Integer OilLevel) {
        this.OilLevel = OilLevel;
    }

    /**
     * 
     * @return
     *     The OilLevelAlarm
     */
    @JsonProperty("OilLevelAlarm")
    public Boolean getOilLevelAlarm() {
        return OilLevelAlarm;
    }

    /**
     * 
     * @param OilLevelAlarm
     *     The OilLevelAlarm
     */
    @JsonProperty("OilLevelAlarm")
    public void setOilLevelAlarm(Boolean OilLevelAlarm) {
        this.OilLevelAlarm = OilLevelAlarm;
    }

    /**
     * 
     * @return
     *     The OilPressureAlarm
     */
    @JsonProperty("OilPressureAlarm")
    public Boolean getOilPressureAlarm() {
        return OilPressureAlarm;
    }

    /**
     * 
     * @param OilPressureAlarm
     *     The OilPressureAlarm
     */
    @JsonProperty("OilPressureAlarm")
    public void setOilPressureAlarm(Boolean OilPressureAlarm) {
        this.OilPressureAlarm = OilPressureAlarm;
    }

    /**
     * 
     * @return
     *     The OilTemperature
     */
    @JsonProperty("OilTemperature")
    public Integer getOilTemperature() {
        return OilTemperature;
    }

    /**
     * 
     * @param OilTemperature
     *     The OilTemperature
     */
    @JsonProperty("OilTemperature")
    public void setOilTemperature(Integer OilTemperature) {
        this.OilTemperature = OilTemperature;
    }

    /**
     * 
     * @return
     *     The OilTemperatureAlarm
     */
    @JsonProperty("OilTemperatureAlarm")
    public Boolean getOilTemperatureAlarm() {
        return OilTemperatureAlarm;
    }

    /**
     * 
     * @param OilTemperatureAlarm
     *     The OilTemperatureAlarm
     */
    @JsonProperty("OilTemperatureAlarm")
    public void setOilTemperatureAlarm(Boolean OilTemperatureAlarm) {
        this.OilTemperatureAlarm = OilTemperatureAlarm;
    }

    /**
     * 
     * @return
     *     The ParkBrakeAlarm
     */
    @JsonProperty("ParkBrakeAlarm")
    public Boolean getParkBrakeAlarm() {
        return ParkBrakeAlarm;
    }

    /**
     * 
     * @param ParkBrakeAlarm
     *     The ParkBrakeAlarm
     */
    @JsonProperty("ParkBrakeAlarm")
    public void setParkBrakeAlarm(Boolean ParkBrakeAlarm) {
        this.ParkBrakeAlarm = ParkBrakeAlarm;
    }

    /**
     * 
     * @return
     *     The ServiceDays
     */
    @JsonProperty("ServiceDays")
    public Integer getServiceDays() {
        return ServiceDays;
    }

    /**
     * 
     * @param ServiceDays
     *     The ServiceDays
     */
    @JsonProperty("ServiceDays")
    public void setServiceDays(Integer ServiceDays) {
        this.ServiceDays = ServiceDays;
    }

    /**
     * 
     * @return
     *     The ServiceRange
     */
    @JsonProperty("ServiceRange")
    public Integer getServiceRange() {
        return ServiceRange;
    }

    /**
     * 
     * @param ServiceRange
     *     The ServiceRange
     */
    @JsonProperty("ServiceRange")
    public void setServiceRange(Integer ServiceRange) {
        this.ServiceRange = ServiceRange;
    }

    /**
     * 
     * @return
     *     The StarterStatus
     */
    @JsonProperty("StarterStatus")
    public String getStarterStatus() {
        return StarterStatus;
    }

    /**
     * 
     * @param StarterStatus
     *     The StarterStatus
     */
    @JsonProperty("StarterStatus")
    public void setStarterStatus(String StarterStatus) {
        this.StarterStatus = StarterStatus;
    }

    /**
     * 
     * @return
     *     The Steering2Alarm
     */
    @JsonProperty("Steering2Alarm")
    public Boolean getSteering2Alarm() {
        return Steering2Alarm;
    }

    /**
     * 
     * @param Steering2Alarm
     *     The Steering2Alarm
     */
    @JsonProperty("Steering2Alarm")
    public void setSteering2Alarm(Boolean Steering2Alarm) {
        this.Steering2Alarm = Steering2Alarm;
    }

    /**
     * 
     * @return
     *     The SteeringAlarm
     */
    @JsonProperty("SteeringAlarm")
    public Boolean getSteeringAlarm() {
        return SteeringAlarm;
    }

    /**
     * 
     * @param SteeringAlarm
     *     The SteeringAlarm
     */
    @JsonProperty("SteeringAlarm")
    public void setSteeringAlarm(Boolean SteeringAlarm) {
        this.SteeringAlarm = SteeringAlarm;
    }

    /**
     * 
     * @return
     *     The Suspension2Alarm
     */
    @JsonProperty("Suspension2Alarm")
    public Boolean getSuspension2Alarm() {
        return Suspension2Alarm;
    }

    /**
     * 
     * @param Suspension2Alarm
     *     The Suspension2Alarm
     */
    @JsonProperty("Suspension2Alarm")
    public void setSuspension2Alarm(Boolean Suspension2Alarm) {
        this.Suspension2Alarm = Suspension2Alarm;
    }

    /**
     * 
     * @return
     *     The Suspension3Alarm
     */
    @JsonProperty("Suspension3Alarm")
    public Boolean getSuspension3Alarm() {
        return Suspension3Alarm;
    }

    /**
     * 
     * @param Suspension3Alarm
     *     The Suspension3Alarm
     */
    @JsonProperty("Suspension3Alarm")
    public void setSuspension3Alarm(Boolean Suspension3Alarm) {
        this.Suspension3Alarm = Suspension3Alarm;
    }

    /**
     * 
     * @return
     *     The SuspensionMajorAlarm
     */
    @JsonProperty("SuspensionMajorAlarm")
    public Boolean getSuspensionMajorAlarm() {
        return SuspensionMajorAlarm;
    }

    /**
     * 
     * @param SuspensionMajorAlarm
     *     The SuspensionMajorAlarm
     */
    @JsonProperty("SuspensionMajorAlarm")
    public void setSuspensionMajorAlarm(Boolean SuspensionMajorAlarm) {
        this.SuspensionMajorAlarm = SuspensionMajorAlarm;
    }

    /**
     * 
     * @return
     *     The SuspensionSpeedAlarm
     */
    @JsonProperty("SuspensionSpeedAlarm")
    public Boolean getSuspensionSpeedAlarm() {
        return SuspensionSpeedAlarm;
    }

    /**
     * 
     * @param SuspensionSpeedAlarm
     *     The SuspensionSpeedAlarm
     */
    @JsonProperty("SuspensionSpeedAlarm")
    public void setSuspensionSpeedAlarm(Boolean SuspensionSpeedAlarm) {
        this.SuspensionSpeedAlarm = SuspensionSpeedAlarm;
    }

    /**
     * 
     * @return
     *     The TireFrontLeftAlarm
     */
    @JsonProperty("TireFrontLeftAlarm")
    public Boolean getTireFrontLeftAlarm() {
        return TireFrontLeftAlarm;
    }

    /**
     * 
     * @param TireFrontLeftAlarm
     *     The TireFrontLeftAlarm
     */
    @JsonProperty("TireFrontLeftAlarm")
    public void setTireFrontLeftAlarm(Boolean TireFrontLeftAlarm) {
        this.TireFrontLeftAlarm = TireFrontLeftAlarm;
    }

    /**
     * 
     * @return
     *     The TireFrontLeftPressure
     */
    @JsonProperty("TireFrontLeftPressure")
    public Double getTireFrontLeftPressure() {
        return TireFrontLeftPressure;
    }

    /**
     * 
     * @param TireFrontLeftPressure
     *     The TireFrontLeftPressure
     */
    @JsonProperty("TireFrontLeftPressure")
    public void setTireFrontLeftPressure(Double TireFrontLeftPressure) {
        this.TireFrontLeftPressure = TireFrontLeftPressure;
    }

    /**
     * 
     * @return
     *     The TireFrontRightAlarm
     */
    @JsonProperty("TireFrontRightAlarm")
    public Boolean getTireFrontRightAlarm() {
        return TireFrontRightAlarm;
    }

    /**
     * 
     * @param TireFrontRightAlarm
     *     The TireFrontRightAlarm
     */
    @JsonProperty("TireFrontRightAlarm")
    public void setTireFrontRightAlarm(Boolean TireFrontRightAlarm) {
        this.TireFrontRightAlarm = TireFrontRightAlarm;
    }

    /**
     * 
     * @return
     *     The TireFrontRightPressure
     */
    @JsonProperty("TireFrontRightPressure")
    public Double getTireFrontRightPressure() {
        return TireFrontRightPressure;
    }

    /**
     * 
     * @param TireFrontRightPressure
     *     The TireFrontRightPressure
     */
    @JsonProperty("TireFrontRightPressure")
    public void setTireFrontRightPressure(Double TireFrontRightPressure) {
        this.TireFrontRightPressure = TireFrontRightPressure;
    }

    /**
     * 
     * @return
     *     The TirePressureAlarm
     */
    @JsonProperty("TirePressureAlarm")
    public Boolean getTirePressureAlarm() {
        return TirePressureAlarm;
    }

    /**
     * 
     * @param TirePressureAlarm
     *     The TirePressureAlarm
     */
    @JsonProperty("TirePressureAlarm")
    public void setTirePressureAlarm(Boolean TirePressureAlarm) {
        this.TirePressureAlarm = TirePressureAlarm;
    }

    /**
     * 
     * @return
     *     The TireRearLeftAlarm
     */
    @JsonProperty("TireRearLeftAlarm")
    public Boolean getTireRearLeftAlarm() {
        return TireRearLeftAlarm;
    }

    /**
     * 
     * @param TireRearLeftAlarm
     *     The TireRearLeftAlarm
     */
    @JsonProperty("TireRearLeftAlarm")
    public void setTireRearLeftAlarm(Boolean TireRearLeftAlarm) {
        this.TireRearLeftAlarm = TireRearLeftAlarm;
    }

    /**
     * 
     * @return
     *     The TireRearLeftPressure
     */
    @JsonProperty("TireRearLeftPressure")
    public Double getTireRearLeftPressure() {
        return TireRearLeftPressure;
    }

    /**
     * 
     * @param TireRearLeftPressure
     *     The TireRearLeftPressure
     */
    @JsonProperty("TireRearLeftPressure")
    public void setTireRearLeftPressure(Double TireRearLeftPressure) {
        this.TireRearLeftPressure = TireRearLeftPressure;
    }

    /**
     * 
     * @return
     *     The TireRearRightAlarm
     */
    @JsonProperty("TireRearRightAlarm")
    public Boolean getTireRearRightAlarm() {
        return TireRearRightAlarm;
    }

    /**
     * 
     * @param TireRearRightAlarm
     *     The TireRearRightAlarm
     */
    @JsonProperty("TireRearRightAlarm")
    public void setTireRearRightAlarm(Boolean TireRearRightAlarm) {
        this.TireRearRightAlarm = TireRearRightAlarm;
    }

    /**
     * 
     * @return
     *     The TireRearRightPressure
     */
    @JsonProperty("TireRearRightPressure")
    public Double getTireRearRightPressure() {
        return TireRearRightPressure;
    }

    /**
     * 
     * @param TireRearRightPressure
     *     The TireRearRightPressure
     */
    @JsonProperty("TireRearRightPressure")
    public void setTireRearRightPressure(Double TireRearRightPressure) {
        this.TireRearRightPressure = TireRearRightPressure;
    }

    /**
     * 
     * @return
     *     The WaterInFuelAlarm
     */
    @JsonProperty("WaterInFuelAlarm")
    public Boolean getWaterInFuelAlarm() {
        return WaterInFuelAlarm;
    }

    /**
     * 
     * @param WaterInFuelAlarm
     *     The WaterInFuelAlarm
     */
    @JsonProperty("WaterInFuelAlarm")
    public void setWaterInFuelAlarm(Boolean WaterInFuelAlarm) {
        this.WaterInFuelAlarm = WaterInFuelAlarm;
    }

    /**
     * 
     * @return
     *     The WaterLevelAlarm
     */
    @JsonProperty("WaterLevelAlarm")
    public Boolean getWaterLevelAlarm() {
        return WaterLevelAlarm;
    }

    /**
     * 
     * @param WaterLevelAlarm
     *     The WaterLevelAlarm
     */
    @JsonProperty("WaterLevelAlarm")
    public void setWaterLevelAlarm(Boolean WaterLevelAlarm) {
        this.WaterLevelAlarm = WaterLevelAlarm;
    }

    /**
     * 
     * @return
     *     The WaterTemperature
     */
    @JsonProperty("WaterTemperature")
    public Integer getWaterTemperature() {
        return WaterTemperature;
    }

    /**
     * 
     * @param WaterTemperature
     *     The WaterTemperature
     */
    @JsonProperty("WaterTemperature")
    public void setWaterTemperature(Integer WaterTemperature) {
        this.WaterTemperature = WaterTemperature;
    }

    /**
     * 
     * @return
     *     The WaterTemperatureAlarm
     */
    @JsonProperty("WaterTemperatureAlarm")
    public Boolean getWaterTemperatureAlarm() {
        return WaterTemperatureAlarm;
    }

    /**
     * 
     * @param WaterTemperatureAlarm
     *     The WaterTemperatureAlarm
     */
    @JsonProperty("WaterTemperatureAlarm")
    public void setWaterTemperatureAlarm(Boolean WaterTemperatureAlarm) {
        this.WaterTemperatureAlarm = WaterTemperatureAlarm;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public static DiagnosticInfo getInstance() {
		return new DiagnosticInfo();
	}

}
