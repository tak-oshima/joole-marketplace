package com.itlize.joolemarketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTypeSearchCriteria {
    private String application;
    private String type;
    private String mountingLocation;
    private String accessories;
    private LocalDate minModelYear;
    private LocalDate maxModelYear;
}
