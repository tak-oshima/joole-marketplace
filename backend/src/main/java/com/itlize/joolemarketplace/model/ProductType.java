package com.itlize.joolemarketplace.model;

import javax.persistence.*;
import java.util.*;
import org.springframework.data.annotation.Id;
@Entity
public class ProductType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;


    private String application;


    private String type;


    private String mountingLocation;


    private String accessories;


    private Date modelYear;

    public ProductType(int productTypeId) {
        this.productTypeId = productTypeId;
    }
    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
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

    public Date getModelYear() {
        return modelYear;
    }

    public void setModelYear(Date modelYear) {
        this.modelYear = modelYear;
    }
}
