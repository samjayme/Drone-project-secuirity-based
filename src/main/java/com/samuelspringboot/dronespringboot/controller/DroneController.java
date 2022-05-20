package com.samuelspringboot.dronespringboot.controller;
import com.samuelspringboot.dronespringboot.dto.DroneDto;
import com.samuelspringboot.dronespringboot.entity.Drone;
import com.samuelspringboot.dronespringboot.entity.Medication;
import com.samuelspringboot.dronespringboot.service.DroneService;
import com.samuelspringboot.dronespringboot.serviceException.DroneLimitExceededException;
import com.samuelspringboot.dronespringboot.serviceException.DroneNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Drone")
public class DroneController {
    private static final Logger log = LoggerFactory.getLogger(DroneController.class);

    @Autowired
    private DroneService droneService;

    // Register a new drone
    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody DroneDto droneDto)  {
        log.info("Registering Drone");
        return  new ResponseEntity<>(droneService.registerDrone(droneDto), HttpStatus.CREATED);


    }




    // For loading Drone with medication using the Drone Id
    @PostMapping("/loadDroneWithMedication")
    public ResponseEntity<Drone> loadDroneWithMedication(@RequestParam("serialNumber") String serialNumber,@RequestBody List<Long> medicationId ) throws DroneNotAvailableException, DroneLimitExceededException {
       log.info("Loading Drone with Medication");
        return  new ResponseEntity<>(droneService.loadDroneWithMed(serialNumber,medicationId),HttpStatus.ACCEPTED);
    }

    // For getting all Drones
    @GetMapping("/findAllDrone")
    public List<Drone> findAllDrone(){
        return droneService.findAllDrone();
    }



    // For checking Drone batteryPercent using Drone Id
    @GetMapping("/checkBattery")
    public Long getBatteryPercentage(@RequestParam("Id") Long Id) throws DroneNotAvailableException {
        log.info("Checking batteryPercent");
        return droneService.checkBatteryPercentage(Id);
    }

    // For check loading items in each Drone using Drone Id
    @GetMapping("/checkLoadedItems")
    public ResponseEntity<List<Medication>> checkLoadedItems(@RequestParam("serialNumber") String serialNumber) throws DroneNotAvailableException {
        log.info("Checking loaded items for a Drone");
        return new ResponseEntity<>(droneService.checkLoadedMedications(serialNumber),HttpStatus.OK);
    }

    // For checking Drones available for loading
    @GetMapping("/checkAvailableDrone")
    public ResponseEntity<List<Drone>> checkAvaliableDrone(){
        log.info("Getting available Drones for loading");
        return new ResponseEntity<>(droneService.getAvailableDrone(),HttpStatus.OK);
    }

    // for getting available drones sorted
    @GetMapping("/checkAvailableDroneSorted")
    public ResponseEntity<List<Drone>> checkAvailableDrone(@RequestParam("sortBy" ) String sortBy){
        log.info("Getting available Drones for loading");
        return new ResponseEntity<>(droneService.getAvailableDroneSorted(sortBy),HttpStatus.OK);
    }

    // for checking loaded medications in drone by serialnumber
    @GetMapping("/checkLoadedMedications")
    public ResponseEntity<List<Medication>> checkLoadedMedications(@RequestParam ("serialNumber")String serialNumber) throws DroneNotAvailableException {
        log.info("checking loaded medications in drone");
        return new ResponseEntity<>(droneService.checkLoadedMedications(serialNumber),HttpStatus.OK);
    }

    // for getting all drones by pages
    @GetMapping("/getAllDronesByPage")
    public ResponseEntity<Page<Drone>> getAllDronesByPages(@RequestParam("offSet") int offSet, @RequestParam("pageSize") int pageSize){
        return new ResponseEntity<>(droneService.findAllDroneWithPagination(offSet,pageSize), HttpStatus.OK);
    }
    // for getting all drones sorted  and by pages
    @GetMapping("/getAllDronesByPageSorted")
    public ResponseEntity<Page<Drone>> getAllDronesByPagesWithSort(
            @RequestParam("offSet") int offSet,
            @RequestParam("pageSize") int pageSze,
            @RequestParam("sortBy") String sortBy
            ){
        log.info("getting all drones sorted and by pages");
        return new ResponseEntity<>(droneService.findAllDroneWithPaginationAndSorting(offSet,pageSze,sortBy), HttpStatus.OK);
    }
}
