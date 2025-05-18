package com.chronicktrack.chronictrack.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.chronicktrack.chronictrack.entity.Consultation;
import com.chronicktrack.chronictrack.repository.ConsultationRepository;

public class ConsultationService {

    
    private final ConsultationRepository consultationRepository;

public ConsultationService(ConsultationRepository consultationRepository) {
this.consultationRepository = consultationRepository;
    }

public void notifyUsersWithUpcomingConsultations() {
List<Consultation> upcoming = consultationRepository.findAll().stream()
.filter(c -> {
LocalDateTime now = LocalDateTime.now();
return c.getDate().isAfter(now) &&
c.getDate().isBefore(now.plusMinutes(30));
})
.collect(Collectors.toList());

for (Consultation c : upcoming) {
System.out.println("Notificación: Consulta próxima para el paciente "
+ c.getPatient().getName() + " con el doctor "
+ c.getDoctor().getName() + " a las "
+ c.getDate());
    }
}
  public Consultation create(Consultation consultation) {
        return consultationRepository.save(consultation); 
    }

     public List<Consultation> findAll() {
        return consultationRepository.findAll(); 
    }
   
 public Optional<Consultation> findById(Long id) {
        return consultationRepository.findById(id); // Fetch consultation by ID
    }

     public void delete(Long id) {
        consultationRepository.deleteById(id); // Use JpaRepository's deleteById method
    }

}
