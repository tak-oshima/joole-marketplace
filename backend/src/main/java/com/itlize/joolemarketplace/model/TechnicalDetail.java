package com.itlize.joolemarketplace.model;


import javax.persistence.*;

@Entity
public class TechnicalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer technicalDetailId;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer airflow;

    private Integer power;
    private Integer operatingVoltage;

    private Integer fanSpeed;

    public Integer getTechnicalDetailId() {
        return technicalDetailId;
    }

    public void setTechnicalDetailId(Integer technicalDetailId) {
        this.technicalDetailId = technicalDetailId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAirFlow() {
        return airflow;
    }

    public void setAirFlow(Integer airflow) {
        this.airflow = airflow;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getOperatingVoltage() {
        return operatingVoltage;
    }

    public void setOperatingVoltage(Integer operatingVoltage) {
        this.operatingVoltage = operatingVoltage;
    }

    public Integer getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(Integer fanSpeed) {
        this.fanSpeed = fanSpeed;
    }
}
