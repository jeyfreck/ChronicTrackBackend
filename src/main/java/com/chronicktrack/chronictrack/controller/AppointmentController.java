package com.chronicktrack.chronictrack.controller;

import java.util.List;

import org.eclipse.angus.mail.iap.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.Appointment;
import com.chronicktrack.chronictrack.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

private final AppointmentService service;

public AppointmentController(AppointmentService service){
this.service = service;
}

@PostMapping
public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
return ResponseEntity.ok(service.save(appointment));
}

@GetMapping
public ResponseEntity<List<Appointment>> getAll(){
return ResponseEntity.ok(service.findAll());
}

@GetMapping("/doctor/{id}")
public ResponseEntity<List<Appointment>> getByDoctor(@PathVariable Long id){
return ResponseEntity.ok(service.findByDoctor(id));
}

@GetMapping("/patient/{id}")
public ResponseEntity<List<Appointment>> getByPatient(@PathVariable Long id){
return ResponseEntity.ok(service.findByPatient(id));
}

@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id){
service.delete(id);
return ResponseEntity.noContent().build();
}
}
