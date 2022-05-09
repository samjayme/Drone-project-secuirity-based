package com.samuelspringboot.dronespringboot.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DroneDto {


    @Size(max = 100, message = "Should not be more than 100 characters")
    @NotBlank(message = "serialNumber cannot be blank")

    @NotBlank(message = "serialNumber cannot be blank")
    private String serialNumber;

    private  String model;
    @Max(value = 500, message = "should not be more than 500grm")
    private Long weightLimit;

    @Max(value = 100 ,message = "Battery percentage should not exceed 100")
    private Long batteryPercentage;
    @NotBlank(message = "Drone state cannot be blank")
    private String state;



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
        return "DroneDto{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model='" + model + '\'' +
                ", weightLimit=" + weightLimit +
                ", batteryPercentage=" + batteryPercentage +
                ", state='" + state + '\'' +
                '}';
    }
}
