package com.chronicktrack.chronictrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.User;
import com.chronicktrack.chronictrack.repository.ChronicConditionRepository;

@Service
public class ChronicConditionService {
    
private final ChronicConditionRepository repository;

public ChronicConditionService(ChronicConditionRepository repository){
this.repository = repository;
}

public ChronicCondition addCondition(String name, String description, User user){
ChronicCondition condition = new ChronicCondition(name,description,user);
return repository.save(condition);
}

public List<ChronicCondition> getConditionsByUser(User user){
return repository.findByUser(user);
}
}
