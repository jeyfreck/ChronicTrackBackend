package com.chronicktrack.chronictrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.ChronicCondition;
import com.chronicktrack.chronictrack.entity.SymptomLog;
import java.util.List;


public interface  SymptomLogRepository extends JpaRepository< SymptomLog, Long>{
List<SymptomLog> findByCondition(ChronicCondition condition);

}
