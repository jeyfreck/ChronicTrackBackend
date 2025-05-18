package com.chronicktrack.chronictrack.service;

import java.util.List;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.Appointment;
import com.chronicktrack.chronictrack.repository.AppointmentRepository;

@Service
public class AppointmentService {

private AppointmentRepository repository;

public AppointmentService(AppointmentRepository repository){
this.repository = repository;
}

public Appointment save(Appointment appointment){
return repository.save(appointment);
}

public List<Appointment> findAll(){
return repository.findAll();
}

public Optional<Appointment> findById(Long id){
return repository.findById(id);
}

public List<Appointment> findByDoctor(Long doctorId){
return repository.findByDoctorId(doctorId);
}

public List<Appointment> findByPatient(Long patientId){
return repository.findByPatientId(patientId);
}

public void delete(Long id){
repository.deleteById(id);
}
}
