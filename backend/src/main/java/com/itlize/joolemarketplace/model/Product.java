package com.itlize.joolemarketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @OneToOne
    @JoinColumn(name = "technical_detail_id")
    private TechnicalDetail technicalDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Description description;

    private String productBrand;

    private String certification;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectProduct> projectProducts;

    public Product() {
        projectProducts = new ArrayList<>();
    }

    public Integer getProductId() {
        return productId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public TechnicalDetail getTechnicalDetail() {
        return technicalDetail;
    }

    public void setTechnicalDetail(TechnicalDetail technicalDetail) {
        this.technicalDetail = technicalDetail;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public List<ProjectProduct> getProjectProducts() {
        return projectProducts;
    }

    public void setProjectProducts(List<ProjectProduct> projectProducts) {
        this.projectProducts = projectProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productType, product.productType) &&
                Objects.equals(technicalDetail, product.technicalDetail) &&
                Objects.equals(description, product.description) &&
                Objects.equals(productBrand, product.productBrand) &&
                Objects.equals(certification, product.certification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productType, technicalDetail, description, productBrand, certification);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productType=" + productType +
                ", technicalDetail=" + technicalDetail +
                ", description=" + description +
                ", productBrand='" + productBrand + '\'' +
                ", certification='" + certification + '\'' +
                '}';
    }
}
