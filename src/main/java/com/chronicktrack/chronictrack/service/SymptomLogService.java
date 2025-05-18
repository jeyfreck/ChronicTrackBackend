package com.chronicktrack.chronictrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.SymptomLog;
import com.chronicktrack.chronictrack.repository.SymptomLogRepository;
@Service
public class SymptomLogService {

private final SymptomLogRepository repository;

public SymptomLogService(SymptomLogRepository repository){
this.repository = repository;
}

public SymptomLog logSymptoms(String symptoms, String notes, ChronicCondition condition){
return repository.save(new SymptomLog(symptoms, notes, condition));
}

public List<SymptomLog> getLogsForCondition(ChronicCondition condition){
return repository.findByCondition(condition);
}
}
