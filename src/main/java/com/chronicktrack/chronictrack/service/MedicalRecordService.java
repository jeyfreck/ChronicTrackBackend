package com.chronicktrack.chronictrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.MedicalRecord;
import com.chronicktrack.chronictrack.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

private final MedicalRecordRepository repository;

public MedicalRecordService(MedicalRecordRepository repository) {
this.repository = repository;
}

public MedicalRecord save(MedicalRecord record){
return repository.save(record);
}

public List<MedicalRecord> getRecordsByPatient(Long patientId){
return repository.findByPatientId(patientId);
}
}
