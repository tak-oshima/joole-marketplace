package com.itlize.joolemarketplace.dto;

public class TechnicalDetailSearchCriteriaDto {
    private Integer minAirflow;
    private Integer maxAirflow;
    private Integer minPower;
    private Integer maxPower;
    private Integer minOperatingVoltage;
    private Integer maxOperatingVoltage;
    private Integer minFanSpeed;
    private Integer maxFanSpeed;

    public TechnicalDetailSearchCriteriaDto() {
    }

    public TechnicalDetailSearchCriteriaDto(Integer minAirflow, Integer maxAirflow, Integer minPower, Integer maxPower, Integer minOperatingVoltage, Integer maxOperatingVoltage, Integer minFanSpeed, Integer maxFanSpeed) {
        this.minAirflow = minAirflow;
        this.maxAirflow = maxAirflow;
        this.minPower = minPower;
        this.maxPower = maxPower;
        this.minOperatingVoltage = minOperatingVoltage;
        this.maxOperatingVoltage = maxOperatingVoltage;
        this.minFanSpeed = minFanSpeed;
        this.maxFanSpeed = maxFanSpeed;
    }

    public Integer getMinAirflow() {
        return minAirflow;
    }

    public void setMinAirflow(Integer minAirflow) {
        this.minAirflow = minAirflow;
    }

    public Integer getMaxAirflow() {
        return maxAirflow;
    }

    public void setMaxAirflow(Integer maxAirflow) {
        this.maxAirflow = maxAirflow;
    }

    public Integer getMinPower() {
        return minPower;
    }

    public void setMinPower(Integer minPower) {
        this.minPower = minPower;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getMinOperatingVoltage() {
        return minOperatingVoltage;
    }

    public void setMinOperatingVoltage(Integer minOperatingVoltage) {
        this.minOperatingVoltage = minOperatingVoltage;
    }

    public Integer getMaxOperatingVoltage() {
        return maxOperatingVoltage;
    }

    public void setMaxOperatingVoltage(Integer maxOperatingVoltage) {
        this.maxOperatingVoltage = maxOperatingVoltage;
    }

    public Integer getMinFanSpeed() {
        return minFanSpeed;
    }

    public void setMinFanSpeed(Integer minFanSpeed) {
        this.minFanSpeed = minFanSpeed;
    }

    public Integer getMaxFanSpeed() {
        return maxFanSpeed;
    }

    public void setMaxFanSpeed(Integer maxFanSpeed) {
        this.maxFanSpeed = maxFanSpeed;
    }

    @Override
    public String toString() {
        return "TechnicalDetailSearchCriteriaDto{" +
                "minAirflow=" + minAirflow +
                ", maxAirflow=" + maxAirflow +
                ", minPower=" + minPower +
                ", maxPower=" + maxPower +
                ", minOperatingVoltage=" + minOperatingVoltage +
                ", maxOperatingVoltage=" + maxOperatingVoltage +
                ", minFanSpeed=" + minFanSpeed +
                ", maxFanSpeed=" + maxFanSpeed +
                '}';
    }
}
