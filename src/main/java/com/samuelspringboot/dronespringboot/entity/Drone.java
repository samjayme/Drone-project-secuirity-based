package com.samuelspringboot.dronespringboot.entity;

import javax.persistence.*;
import java.util.List;


@Entity
public class Drone {

    @Id
    @Column(name = "droneId")
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long droneId;

    private String serialNumber;
    private  String model;
    private Long weightLimit;
    private Long batteryPercentage;
    private String state;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Medication> medicationList ;

    public List<Medication> getMedicationList()
    {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;


    }

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Long weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Long getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(Long batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "droneId=" + droneId +
                ", serialNumber='" + serialNumber + '\'' +
                ", model='" + model + '\'' +
                ", weightLimit=" + weightLimit +
                ", batteryPercentage=" + batteryPercentage +
                ", state='" + state + '\'' +
                ", medicationList=" + medicationList +
                '}';
    }
}
