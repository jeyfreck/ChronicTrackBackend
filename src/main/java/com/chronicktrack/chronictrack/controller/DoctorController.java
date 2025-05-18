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

import com.chronicktrack.chronictrack.entity.Doctor;
import com.chronicktrack.chronictrack.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    
private final DoctorService service;

public DoctorController(DoctorService service){
this.service = service;
}

@PostMapping
public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
return ResponseEntity.ok(service.save(doctor));
}

@GetMapping
public ResponseEntity<List<Doctor>> getAllDoctors(){
return ResponseEntity.ok(service.findAll());
}

@GetMapping("/{id}")
public ResponseEntity<Doctor> getDoctor(@PathVariable Long id){
return service.findById(id).map(ResponseEntity::ok).orElse(
ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteDoctor(@PathVariable Long id){
service.delete(id);
return ResponseEntity.noContent().build();
}
}
