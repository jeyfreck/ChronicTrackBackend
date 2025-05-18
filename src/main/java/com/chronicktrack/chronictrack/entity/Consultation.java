package com.chronicktrack.chronictrack.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Consultation {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private String diagnosis;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

     @ManyToOne
    private Doctor doctor;

    public Consultation() {}

    public Consultation(String reason, String diagnosis, LocalDateTime date, Patient patient) {
        this.reason = reason;
        this.diagnosis = diagnosis;
        this.date = date;
        this.patient = patient;
    }

    // Getters y setters
    public Long getId() { return id; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }

    public String getDiagnosis() { return diagnosis; }

    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    
}

