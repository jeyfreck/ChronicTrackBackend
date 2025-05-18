package com.chronicktrack.chronictrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.Consultation;

public interface  ConsultationRepository extends JpaRepository<Consultation, Long>{
 List<Consultation> findByPatientId(Long patientId);
public Consultation create(Consultation consultation);


}
