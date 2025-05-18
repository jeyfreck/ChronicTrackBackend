package com.chronicktrack.chronictrack.notification;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.chronicktrack.chronictrack.service.ConsultationService;

@Component
public class NotificationScheduler {

private final ConsultationService consultationService;

public NotificationScheduler(ConsultationService consultationService){
this.consultationService = consultationService;
}
@GetMapping("/api/notifications")
@Scheduled(fixedRate = 10000)
public void checkUpcomingConsultations(){
System.out.println("Checking for upcoming consultations...");
consultationService.notifyUsersWithUpcomingConsultations();
}
}
