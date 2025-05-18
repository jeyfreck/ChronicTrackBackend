package com.chronicktrack.chronictrack.controller;


// Remove this if it's not needed

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.chronicktrack.chronictrack.entity.Consultation;
import com.chronicktrack.chronictrack.entity.Patient;
import com.chronicktrack.chronictrack.repository.ConsultationRepository;
import com.chronicktrack.chronictrack.repository.PatientRepository;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;

    public ReportController(PatientRepository patientRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getPatientReport(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }

        List<Consultation> consultations = consultationRepository.findByPatientId(id);
        return ResponseEntity.ok(consultations);
    }
}