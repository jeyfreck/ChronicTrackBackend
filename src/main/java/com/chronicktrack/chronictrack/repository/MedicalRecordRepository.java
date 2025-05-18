package com.chronicktrack.chronictrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.MedicalRecord;


public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long>{
List<MedicalRecord> findByPatientId(Long patientId);
}
