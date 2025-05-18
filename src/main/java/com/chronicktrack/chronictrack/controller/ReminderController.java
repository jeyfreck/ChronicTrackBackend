package com.chronicktrack.chronictrack.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chronicktrack.chronictrack.entity.Reminder;
import com.chronicktrack.chronictrack.entity.Treatment;
import com.chronicktrack.chronictrack.repository.TreatmentRepository;
import com.chronicktrack.chronictrack.service.ReminderExportService;
import com.chronicktrack.chronictrack.service.ReminderService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    
private final ReminderService reminderService;
private final TreatmentRepository treatmentRepo;
private final ReminderExportService reminderExportService;

public ReminderController(ReminderService reminderService, TreatmentRepository treatmentRepo, 
ReminderExportService reminderExportService){
this.reminderService = reminderService;
this.treatmentRepo = treatmentRepo;
this.reminderExportService = reminderExportService;
}

@PostMapping("/{treatmentId}")
public ResponseEntity<Reminder> createReminder(@PathVariable Long treatmentId, 
@Valid @RequestBody Reminder reminderRequest){

Treatment treatment = treatmentRepo.findById(treatmentId).orElse(null);
if(treatment == null) {
return ResponseEntity.badRequest().build();

}

Reminder saved = reminderService.createReminder(reminderRequest.getMessage(), 
reminderRequest.getDateTime(), treatment);
return ResponseEntity.ok(saved);
}

@GetMapping("/{treatmentId}")
public ResponseEntity<List<Reminder>> getReminders(@PathVariable Long treatmentId){
Treatment treatment = treatmentRepo.findById(treatmentId).orElse(null);
if(treatment == null){
return ResponseEntity.badRequest().build();
}
return ResponseEntity.ok(reminderService.getRemindersForTreatment(treatment));
}

@GetMapping("/{treatmentId}/reminders/export")
public void exportRemindersToCsv(@PathVariable Long treatmentId, HttpServletResponse 
response) throws IOException{
response.setContentType("text/csv");
response.setHeader("Contend-Disposition","attachement:filename=reminders.csv");
}

@GetMapping("/export")
public ResponseEntity<byte[]> exportReminders(
    @RequestParam(required = false) String username,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    @RequestParam(required = false) String treatment
) {
    byte[] pdfData = reminderExportService.exportRemindersToPdf(username, date, treatment);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("attachment", "reminders.pdf");

    return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
}

}
