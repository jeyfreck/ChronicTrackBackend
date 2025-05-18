package com.chronicktrack.chronictrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    
}
