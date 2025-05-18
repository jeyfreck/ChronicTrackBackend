package com.chronicktrack.chronictrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    
}
