package com.chronicktrack.chronictrack.service;

import java.util.List;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chronicktrack.chronictrack.entity.User;
import com.chronicktrack.chronictrack.repository.UserRepository;

@Component
public class ScheduledAlertSender {

private final UserRepository userRepository;
private final HealthAlertService healthAlertService;

public ScheduledAlertSender(UserRepository userRepository, HealthAlertService healthAlertService){
this.userRepository = userRepository;
this.healthAlertService = healthAlertService;
}

@Scheduled(cron = "0 0 8 * * ?")
public void sendDailyAlert(){
List<User> users = userRepository.findAll();
for(User user : users){
healthAlertService.notifyUserByEmail(user);
}
}

}
