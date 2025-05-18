package com.chronicktrack.chronictrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.Patient;
import com.chronicktrack.chronictrack.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

private final PatientService service;

public PatientController(PatientService service){
this.service = service;
}

@PostMapping
public ResponseEntity<Patient> create(@RequestBody Patient patient){
return ResponseEntity.ok(service.save(patient));
}

@GetMapping
public ResponseEntity<List<Patient>> getAll(){
return ResponseEntity.ok(service.findAll());
}

@GetMapping("/{id}")
public ResponseEntity<Patient> getById(@PathVariable Long id){
return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id){
service.delete(id);
return ResponseEntity.noContent().build();
}
}
