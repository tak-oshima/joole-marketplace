package com.itlize.joolemarketplace.model;


import javax.persistence.*;
import java.util.*;
import org.springframework.data.annotation.Id;

@Entity
public class TechnicalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int technicalDetailId;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int airflow;

    private int power;
    private int operatingVoltage;

    private int fanSpeed;

    public TechnicalDetail(){}
    public TechnicalDetail(int technicalDetailId) {
        this.technicalDetailId = technicalDetailId;
    }

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

    public int getAirflow() {
        return airflow;
    }

    public void setAirflow(int airflow) {
        this.airflow = airflow;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getOperatingVoltage() {
        return operatingVoltage;
    }

    public void setOperatingVoltage(int operatingVoltage) {
        this.operatingVoltage = operatingVoltage;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
    }
}
