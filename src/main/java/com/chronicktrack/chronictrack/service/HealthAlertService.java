package com.chronicktrack.chronictrack.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.Reminder;
import com.chronicktrack.chronictrack.entity.User;
import com.chronicktrack.chronictrack.repository.ReminderRepository;
import com.chronicktrack.chronictrack.repository.UserRepository;


@Service
public class HealthAlertService {

private final ReminderRepository reminderRepository;
private final UserRepository userRepository;
private final EmailService emailService;

public HealthAlertService(ReminderRepository reminderRepository, UserRepository userRepository, 
EmailService emailService){
this.reminderRepository = reminderRepository;
this.userRepository = userRepository;
this.emailService = emailService;
}

public List<String> generateAlertsForUser(String username){
List<String> alerts = new ArrayList<>();
Optional<User> userOpt = userRepository.findByUsername(username);

if(userOpt.isEmpty()){
alerts.add("User not found");
}

User user = userOpt.get();
LocalDate today = LocalDate.now();

List<Reminder> upcoming = reminderRepository.findByUserAndDateBetween(user, today, 
today.plusDays(3));
for(Reminder reminder : upcoming){
alerts.add("Upcoming reminder" + reminder.getTreatment()+ " of " + reminder.getDateTime() );
}

List<Reminder> recent = reminderRepository.findByUserAndDateAfter(user, today
.minusDays(7));
if(recent.isEmpty()){
alerts.add("You haven´t logged any recent activities. Is everything okay?");

}

return alerts;
}

public void notifyUserByEmail(User user){
List<String> alerts = generateAlertsForUser(user.getUsername());

if(!alerts.isEmpty()){
String message = String.join("\n", alerts);
emailService.sendEmail(user.getEmail(), " Health notifications - Reminder ", 
message);
}
}
}
