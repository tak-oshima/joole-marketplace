package com.itlize.joolemarketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalDetailSearchCriteria {
    private Integer minAirflow;
    private Integer maxAirflow;
    private Integer minPower;
    private Integer maxPower;
    private Integer minOperatingVoltage;
    private Integer maxOperatingVoltage;
    private Integer minFanSpeed;
    private Integer maxFanSpeed;
}
