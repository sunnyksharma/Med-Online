package com.medonline.medicine.controller;

import com.medonline.medicine.Exception.MedOnlineMedicineException;
import com.medonline.medicine.dto.MedicineDTO;
import com.medonline.medicine.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicine")
public class MedicineController {

    @Autowired
    MedicineService service;

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineDTO> getMedicineByID(@PathVariable Integer medicineId) throws MedOnlineMedicineException {
        return new ResponseEntity<>(service.getMedicineById(medicineId), HttpStatus.OK);
    }
}
