package com.chronicktrack.chronictrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChronicCondition {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String conditionName;
private String description;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

public ChronicCondition(){}

public ChronicCondition(String conditionName, String description, User user) {
    this.conditionName = conditionName;
    this.description = description;
    this.user = user;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getConditionName() {
    return conditionName;
}

public void setConditionName(String conditionName) {
    this.conditionName = conditionName;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}


}
