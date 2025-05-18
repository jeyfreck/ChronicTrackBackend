package com.chronicktrack.chronictrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.MedicalRecord;
import com.chronicktrack.chronictrack.service.MedicalRecordService;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {
    
private final MedicalRecordService service;

public MedicalRecordController(MedicalRecordService service){
this.service =  service;
}

@PostMapping
public ResponseEntity<MedicalRecord> createRecord(@RequestBody MedicalRecord record){
return ResponseEntity.ok(service.save(record));
}

@GetMapping("/patient/{id}")
public ResponseEntity<List<MedicalRecord>> getByPatientId(@PathVariable Long id) {
 return ResponseEntity.ok(service.getRecordsByPatient(id));
}
}
