package com.itlize.joolemarketplace.dto;

import java.time.LocalDate;

public class ProductTypeSearchCriteriaDto {
    private String application;
    private String type;
    private String mountingLocation;
    private String accessories;
    private LocalDate minModelYear;
    private LocalDate maxModelYear;

    public ProductTypeSearchCriteriaDto() {
    }

    public ProductTypeSearchCriteriaDto(String application, String type, String mountingLocation, String accessories, LocalDate minModelYear, LocalDate maxModelYear) {
        this.application = application;
        this.type = type;
        this.mountingLocation = mountingLocation;
        this.accessories = accessories;
        this.minModelYear = minModelYear;
        this.maxModelYear = maxModelYear;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMountingLocation() {
        return mountingLocation;
    }

    public void setMountingLocation(String mountingLocation) {
        this.mountingLocation = mountingLocation;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public LocalDate getMinModelYear() {
        return minModelYear;
    }

    public void setMinModelYear(LocalDate minModelYear) {
        this.minModelYear = minModelYear;
    }

    public LocalDate getMaxModelYear() {
        return maxModelYear;
    }

    public void setMaxModelYear(LocalDate maxModelYear) {
        this.maxModelYear = maxModelYear;
    }

    @Override
    public String toString() {
        return "ProductTypeSearchCriteriaDto{" +
                "application='" + application + '\'' +
                ", type='" + type + '\'' +
                ", mountingLocation='" + mountingLocation + '\'' +
                ", accessories='" + accessories + '\'' +
                ", minModelYear=" + minModelYear +
                ", maxModelYear=" + maxModelYear +
                '}';
    }
}
