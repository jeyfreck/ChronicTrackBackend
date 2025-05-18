package com.chronicktrack.chronictrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.Treatment;
import java.util.List;


public interface  TreatmentRepository extends JpaRepository<Treatment, Long>{
List<Treatment> findByCondition(ChronicCondition condition);

}
