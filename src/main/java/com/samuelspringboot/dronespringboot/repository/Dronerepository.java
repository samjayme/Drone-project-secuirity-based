package com.samuelspringboot.dronespringboot.repository;

import com.samuelspringboot.dronespringboot.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Dronerepository extends JpaRepository<Drone,Long> {
    Drone findBySerialNumber(String serialNumber );
}
