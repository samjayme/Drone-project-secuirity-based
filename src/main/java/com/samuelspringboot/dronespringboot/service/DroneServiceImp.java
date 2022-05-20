package com.samuelspringboot.dronespringboot.service;

import com.samuelspringboot.dronespringboot.dto.DroneDto;
import com.samuelspringboot.dronespringboot.entity.Drone;
import com.samuelspringboot.dronespringboot.entity.Medication;
import com.samuelspringboot.dronespringboot.repository.Dronerepository;
import com.samuelspringboot.dronespringboot.repository.MedicationRepository;
import com.samuelspringboot.dronespringboot.serviceException.DroneLimitExceededException;
import com.samuelspringboot.dronespringboot.serviceException.DroneNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DroneServiceImp implements DroneService {

    @Autowired
    private Dronerepository dronerepository;
    @Autowired
    private MedicationRepository medicationRepository;
    private static final Long batteryLimit = 25L;
    private static final String stateForLoading = "IDLE";


    // REGISTERING NEW DRONE
    @Override
    public Drone registerDrone(DroneDto droneDto) {
        Drone drone = new Drone();
        // CONVERTING ENTITY TO DTO
        drone.setState(droneDto.getState());
        drone.setSerialNumber(droneDto.getSerialNumber());
        drone.setBatteryPercentage(droneDto.getBatteryPercentage());
        drone.setModel(droneDto.getModel());
        drone.setWeightLimit(droneDto.getWeightLimit());


        return dronerepository.save(drone);
    }

    // LOADING DRONE WITH MEDICATION/S
    @Override
    public Drone loadDroneWithMed(String serialNumber, List<Long> medicationId) throws DroneNotAvailableException, DroneLimitExceededException {
        Drone drone = dronerepository.findAll().stream().filter(drone1 -> drone1.getSerialNumber().equals(serialNumber))
                .findAny().orElseThrow(()-> new DroneNotAvailableException("Drone NOT AVAILABLE FOR LOADING"));
        Long droneLimit = drone.getWeightLimit();
        String droneState = drone.getState();
        if (drone.getBatteryPercentage() < batteryLimit) {
            throw new RuntimeException("Drone battery is too low for loading");
        }

        List<Medication> medications = (medicationRepository.findAllById(medicationId));
        long medicationWeight=medications.stream().map(medication -> medication.getWeight()).mapToLong(i->i).sum();

        if (medicationWeight > droneLimit) {
            throw new DroneLimitExceededException("Drone with serialNumber: " + serialNumber + " " + "can only carry :"
                    + droneLimit + "gram" + " " + "Of medication"
                    + " you tried adding" + " " + medicationWeight + "gram" + " " + "Of medication");
        } else if (!drone.getState().equals(stateForLoading)) {
            throw new DroneNotAvailableException("Drone NOT AVAILABLE FOR LOADING");
        } else {
            drone.setMedicationList(medications);
        }

        dronerepository.save(drone);
        return drone;
    }

    // GETTING ALL DRONES
    @Override
    public List<Drone> findAllDrone() {
        return new ArrayList<>(dronerepository.findAll());
    }

    // GETTING ALL DRONES BY PAGE
    @Override
    public Page<Drone> findAllDroneWithPagination(int offSet, int pageSize) {
        return dronerepository.findAll(PageRequest.of(offSet,pageSize));
    }

    // GETTING ALL DRONES SORTED BY PAGE
    @Override
    public Page<Drone> findAllDroneWithPaginationAndSorting(int offSet, int pageSize, String sortBy) {
        return dronerepository.findAll(PageRequest.of(offSet,pageSize).withSort(Sort.Direction.DESC,sortBy));
    }

    // GETTING DRONES AVAILABLE FOR LOADING
    @Override
    public List<Drone> getAvailableDrone() {
        List<Drone> drone = dronerepository.findAll();

        return drone.stream()
                .filter(d -> d.getBatteryPercentage() > batteryLimit && d.getState().equals(stateForLoading)).collect(Collectors.toList());
    }

    // GETTING AVAILABLE DRONES SORTED
    @Override
    public List<Drone> getAvailableDroneSorted(String sortBy) {
        return dronerepository.findAll(Sort.by(Sort.Direction.DESC,sortBy));
    }


    // CHECKING DRONE BATTERY PERCENTAGE BY DRONE ID
    @Override
    public Long checkBatteryPercentage(Long id) throws DroneNotAvailableException {
        Drone drone = dronerepository.findById(id).orElseThrow(() -> new DroneNotAvailableException("drone with " + " "
                + id + " " + " not available"));


        return drone.getBatteryPercentage();
    }

    // CHECK LOADED ITEMS BY SERIAL NUMBER

    @Override
    public List<Medication> checkLoadedMedications(String serialNumber) throws DroneNotAvailableException {
        Drone drone = dronerepository.findAll().stream().filter(drone1 -> drone1.getSerialNumber().equals(serialNumber))
                .findAny().orElseThrow(()-> new  DroneNotAvailableException("Drone "+ " " +serialNumber +" "+ "not available"));

        List<Medication> listOfLoadedMedication = new ArrayList<>(drone.getMedicationList());;
        return listOfLoadedMedication;

    }




    // GETTING DRONES BY MODEL
    @Override
    public List<String> findDroneModel() {
        List<Drone> droneList = dronerepository.findAll();
       return droneList.stream().map(drone -> drone.getModel()).toList();
    }

    // CHECKING BATTERY LEVEL FOR ALL DRONES
    // THIS IS A PERIODIC SCHEDULED TASK IMPLEMENTED IN THE DRONESCHEDULER CLASS ON THE SCHEDULER PACKAGE
    @Override
    public String checkDroneBatteryLevel() {
        List<Drone> droneList = dronerepository.findAll();

        for (Drone d : droneList) {
            System.out.println("...............BATTERY CHECK ROUTINE............. ");
            System.out.println(d.getSerialNumber() + " " + "battery level is" + " " + d.getBatteryPercentage());

        }

        return "Drone";
    }


}




