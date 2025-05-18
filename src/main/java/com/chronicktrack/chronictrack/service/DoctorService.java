package com.chronicktrack.chronictrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.Doctor;
import com.chronicktrack.chronictrack.repository.DoctorRepository;

@Service
public class DoctorService {

private final DoctorRepository repository;

public DoctorService(DoctorRepository repository){
this.repository = repository;
}

public Doctor save(Doctor doctor){
return repository.save(doctor);
}

public List<Doctor> findAll(){
return repository.findAll();
}

public Optional<Doctor> findById(Long id){
return repository.findById(id);
}

public void delete(Long id){
repository.deleteById(id);
}
}
