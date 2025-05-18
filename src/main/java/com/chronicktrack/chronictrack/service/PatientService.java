package com.chronicktrack.chronictrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.Patient;
import com.chronicktrack.chronictrack.repository.PatientRepository;

@Service
public class PatientService {

private final PatientRepository repository;

public PatientService(PatientRepository repository){
this.repository = repository;
}

public Patient save(Patient patient){
return repository.save(patient);
}

public List<Patient> findAll(){
return repository.findAll();
}

public Optional<Patient> findById(Long id){
return repository.findById(id);
}

public void delete(Long id){
repository.deleteById(id);
}
}
