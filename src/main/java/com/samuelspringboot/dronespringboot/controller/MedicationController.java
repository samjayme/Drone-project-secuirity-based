package com.samuelspringboot.dronespringboot.controller;

import com.samuelspringboot.dronespringboot.dto.MedicationDto;
import com.samuelspringboot.dronespringboot.entity.Medication;
import com.samuelspringboot.dronespringboot.service.MedictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MedicationController {

    @Autowired
    MedictionService medictionService;

    @PostMapping("/medication/saveMedication")
    public ResponseEntity<Medication> saveMedication(@Valid @RequestBody MedicationDto medicationDto) {
        return new ResponseEntity<>(medictionService.saveMedication(medicationDto), HttpStatus.CREATED);
    }
}
