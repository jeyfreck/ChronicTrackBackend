package com.chronicktrack.chronictrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.User;

public interface  ChronicConditionRepository extends JpaRepository<ChronicCondition, 
Long>{

List<ChronicCondition> findByUser(User user);
}
