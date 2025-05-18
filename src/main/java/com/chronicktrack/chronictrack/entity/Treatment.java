package com.chronicktrack.chronictrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Treatment {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String medication;
private String dosage;
private String frequency;

@ManyToOne
@JoinColumn(name = "condition_id")
private ChronicCondition condition;

 private String name;

public Treatment(){}

public Treatment( String medication, String dosage, String frequency, ChronicCondition condition) {
    this.medication = medication;
    this.dosage = dosage;
    this.frequency = frequency;
    this.condition = condition;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getMedication() {
    return medication;
}

public void setMedication(String medication) {
    this.medication = medication;
}

public String getDosage() {
    return dosage;
}

public void setDosage(String dosage) {
    this.dosage = dosage;
}

public String getFrequency() {
    return frequency;
}

public void setFrequency(String frequency) {
    this.frequency = frequency;
}

public ChronicCondition getCondition() {
    return condition;
}

public void setCondition(ChronicCondition condition) {
    this.condition = condition;
}

  
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

}
