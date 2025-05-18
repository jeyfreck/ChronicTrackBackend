package com.chronicktrack.chronictrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.Treatment;
import com.chronicktrack.chronictrack.repository.ChronicConditionRepository;
import com.chronicktrack.chronictrack.service.TreatmentService;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

private final TreatmentService treatmentService;
private final ChronicConditionRepository conditionRepo;

public TreatmentController(TreatmentService treatmentService, ChronicConditionRepository 
conditionRepo){
this.treatmentService = treatmentService;
this.conditionRepo = conditionRepo;
}

@PostMapping("/{conditionId}")
public ResponseEntity<Treatment> assignTreatment(@PathVariable Long conditionId, 
@RequestBody Treatment payload){

ChronicCondition condition = conditionRepo.findById(conditionId).orElse(null);
if(condition == null){
return ResponseEntity.badRequest().build();
}

Treatment saved = treatmentService.assignTreatment(payload.getMedication(), 
payload.getDosage(), payload.getFrequency(), condition);
return ResponseEntity.ok(saved);
}

@GetMapping("/{conditionId}")
public ResponseEntity<List<Treatment>> getTreatments(@PathVariable Long conditionId){
ChronicCondition condition = conditionRepo.findById(conditionId).orElse(null);
if(condition == null){
return ResponseEntity.badRequest().build();
}

return ResponseEntity.ok(treatmentService.getTreatmentsForCondition(condition));
}
}
