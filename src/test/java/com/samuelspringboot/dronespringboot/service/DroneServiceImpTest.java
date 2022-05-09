package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.entity.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DroneServiceImpTest {

    @BeforeEach
    void setUp() {

    }

    @Autowired
    private DroneService droneService;

    @Test
    void registerDrone() {


    }

    @Test
    void loadDroneWithMed() {
    }

    @Test
    void findAllDrone() {
    }

    @Test
    void getAvailableDrone() {
    }

    @Test
    void checkBatteryPercentage() {
    }

    @Test
    void checkLoadedItems() {
    }

    @Test
    void checkDroneBatteryLevel() {
    }
}