package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.dto.DroneDto;
import com.samuelspringboot.dronespringboot.entity.Drone;
import com.samuelspringboot.dronespringboot.entity.Medication;
import com.samuelspringboot.dronespringboot.serviceException.DroneLimitExceededException;
import com.samuelspringboot.dronespringboot.serviceException.DroneNotAvailableException;
import org.springframework.data.domain.Page;


import java.util.List;


public interface DroneService {

    Drone registerDrone(DroneDto droneDto) ;

    Drone loadDroneWithMed(String serialNumber , List<Long> medicationId) throws DroneNotAvailableException, DroneLimitExceededException;

    List<Drone> findAllDrone();

    Page<Drone> findAllDroneWithPagination(int offSet, int pageSize);
    Page<Drone> findAllDroneWithPaginationAndSorting(int offSet, int pageSize, String sortBy);


    List<Drone> getAvailableDrone();
    List<Drone> getAvailableDroneSorted(String sortBy);
    Long checkBatteryPercentage(Long id) throws DroneNotAvailableException;

    Drone checkLoadedItems(Long droneId) throws DroneNotAvailableException;




    // GETTING DRONES BY MODEL
    List<Medication> checkLoadedMedications(String serialNumber) throws DroneNotAvailableException;



    // GETTING DRONES BY MODEL
    List<String> findDroneModel(String model);

    // FOE DRONE BATTERY LEVE ROUTINE CHECK
    String checkDroneBatteryLevel();



}
