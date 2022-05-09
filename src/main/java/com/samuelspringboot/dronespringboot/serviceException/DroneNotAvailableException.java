package com.samuelspringboot.dronespringboot.serviceException;

public class DroneNotAvailableException extends Exception{
    public DroneNotAvailableException(String message) {

        super((message));
    }
}
