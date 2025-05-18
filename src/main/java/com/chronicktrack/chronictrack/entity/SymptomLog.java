package com.chronicktrack.chronictrack.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SymptomLog {
   
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDate date;

private String symptoms;
private String notes;

@ManyToOne
@JoinColumn(name = "condition_id")
private ChronicCondition condition;

public SymptomLog(){
this.date = LocalDate.now();
}

public SymptomLog(String symptoms, String notes, ChronicCondition condition) {
    this.date = LocalDate.now();
    this.symptoms = symptoms;
    this.notes = notes;
    this.condition = condition;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public String getSymptoms() {
    return symptoms;
}

public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
}

public String getNotes() {
    return notes;
}

public void setNotes(String notes) {
    this.notes = notes;
}

public ChronicCondition getCondition() {
    return condition;
}

public void setCondition(ChronicCondition condition) {
    this.condition = condition;
}


}
