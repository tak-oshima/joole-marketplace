package com.itlize.joolemarketplace.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productTypeId;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String application;

    private String type;

    private String mountingLocation;

    private String accessories;

    private LocalDate modelYear;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public LocalDate getModelYear() {
        return modelYear;
    }

    public void setModelYear(LocalDate modelYear) {
        this.modelYear = modelYear;
    }
}
