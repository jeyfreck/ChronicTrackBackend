package com.chronicktrack.chronictrack.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chronicktrack.chronictrack.entity.Reminder;
import com.chronicktrack.chronictrack.entity.Treatment;
import com.chronicktrack.chronictrack.entity.User;


public interface  ReminderRepository extends JpaRepository<Reminder, Long>{
List<Reminder> findByTreatment(Treatment treatment);
 List<Reminder> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
 List<Reminder> findByUserAndDateAfter(User user, LocalDate date); 
}
