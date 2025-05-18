package com.chronicktrack.chronictrack.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDateTime dateTime;

@ManyToOne
@JoinColumn(name = "doctor_id")
private Doctor doctor;

@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;

private String reason;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public LocalDateTime getDateTime() {
    return dateTime;
}

public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
}

public Doctor getDoctor() {
    return doctor;
}

public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
}

public Patient getPatient() {
    return patient;
}

public void setPatient(Patient patient) {
    this.patient = patient;
}

public String getReason() {
    return reason;
}

public void setReason(String reason) {
    this.reason = reason;
}


}
