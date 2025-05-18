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

import com.chronicktrack.chronictrack.entity.Consultation;
import com.chronicktrack.chronictrack.service.ConsultationService;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {
    
private final ConsultationService service;

public ConsultationController(ConsultationService service){
this.service = service;
}

@PostMapping
public ResponseEntity<Consultation> create(@RequestBody Consultation consultation){
return ResponseEntity.ok(service.create(consultation));
}

@GetMapping
public ResponseEntity<List<Consultation>> findAll(){
return ResponseEntity.ok(service.findAll());
}

@GetMapping("/{id}")
public ResponseEntity<Consultation> findById(@PathVariable Long id){
return ResponseEntity.of(service.findById(id));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id){
service.delete(id);
return ResponseEntity.noContent().build();
}
}
