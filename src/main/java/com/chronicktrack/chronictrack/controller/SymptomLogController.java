package com.chronicktrack.chronictrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.SymptomLog;
import com.chronicktrack.chronictrack.repository.ChronicConditionRepository;
import com.chronicktrack.chronictrack.service.SymptomLogService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/symptoms")
public class SymptomLogController {
    
private final SymptomLogService logService;
private final ChronicConditionRepository conditionRepo;

public SymptomLogController(SymptomLogService logService, ChronicConditionRepository conditionRepo){
this.logService = logService;
this.conditionRepo = conditionRepo;
}

@PostMapping("/{conditionId}")
public ResponseEntity<SymptomLog> logSymptoms(@PathVariable Long conditionId, 
@RequestBody SymptomLog payload){

ChronicCondition condition = conditionRepo.findById(conditionId).orElse(null);
if(condition == null){
return ResponseEntity.badRequest().build();
}

SymptomLog saved = logService.logSymptoms(payload.getSymptoms(), payload.getNotes(), 
condition);
return ResponseEntity.ok(saved);
}

@GetMapping("/{conditionId}")
public ResponseEntity<List<SymptomLog>> getLogs(@PathVariable Long conditionId) {
ChronicCondition condition = conditionRepo.findById(conditionId).orElse(null);
if(condition == null){
return ResponseEntity.badRequest().build();
}

return ResponseEntity.ok(logService.getLogsForCondition(condition));
}

}
