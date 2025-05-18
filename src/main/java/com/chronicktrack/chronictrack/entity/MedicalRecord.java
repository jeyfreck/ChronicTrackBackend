package com.chronicktrack.chronictrack.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MedicalRecord {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String diagnosis;

private String treatment;

private LocalDate date;

@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getDiagnosis() {
    return diagnosis;
}

public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
}

public String getTreatment() {
    return treatment;
}

public void setTreatment(String treatment) {
    this.treatment = treatment;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public Patient getPatient() {
    return patient;
}

public void setPatient(Patient patient) {
    this.patient = patient;
}


}
