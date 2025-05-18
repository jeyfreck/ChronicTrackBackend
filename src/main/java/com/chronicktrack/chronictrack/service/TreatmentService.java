package com.chronicktrack.chronictrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.Treatment;
import com.chronicktrack.chronictrack.repository.TreatmentRepository;

@Service
public class TreatmentService {

private final TreatmentRepository repository;

public TreatmentService(TreatmentRepository repository){
this.repository = repository;
}

public Treatment assignTreatment(String medication, String dosage, String frecuency, 
ChronicCondition condition){
return repository.save(new Treatment(medication, dosage, frecuency, condition));
}

public List<Treatment> getTreatmentsForCondition(ChronicCondition condition){
return repository.findByCondition(condition);
}
}
