package com.chronicktrack.chronictrack.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.Reminder;
import com.chronicktrack.chronictrack.entity.Treatment;
import com.chronicktrack.chronictrack.repository.ReminderRepository;
import com.chronicktrack.chronictrack.repository.TreatmentRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ReminderExportService {

    private final ReminderRepository reminderRepository;
    private final TreatmentRepository treatmentRepo;
    

    public ReminderExportService(ReminderRepository reminderRepository, TreatmentRepository treatmentRepo) {
        this.reminderRepository = reminderRepository;
        this.treatmentRepo = treatmentRepo;
    }

    public void writeRemindersToCsv(Long treatmentId, PrintWriter writer) throws IllegalArgumentException {
        Treatment treatment = treatmentRepo.findById(treatmentId)
                .orElseThrow(() -> new IllegalArgumentException("Treatment not found"));
        List<Reminder> reminders = reminderRepository.findByTreatment(treatment);

        for (Reminder reminder : reminders) {
            writer.println(String.format("%d,%s,%s",
                    reminder.getId(),
                    reminder.getMessage().replace(",", ","),
                    reminder.getDateTime().toString()));
        }
    }

  private byte[] generatePdf(List<Reminder> reminders) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            
            document.add(new Paragraph("Reminders List"));

            
            for (Reminder reminder : reminders) {
                document.add(new Paragraph(String.format("ID: %d, Message: %s, DateTime: %s",
                        reminder.getId(),
                        reminder.getMessage(),
                        reminder.getDateTime().toString())));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace(); 

         }

        return out.toByteArray();
    }

     public byte[] exportRemindersToPdf(String username, LocalDate date, String treatment) {
        
        List<Reminder> reminders = reminderRepository.findAll();

        
        if (username != null) {
            reminders = reminders.stream()
                .filter(r -> r.getUser().getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
        }

        
        if (date != null) {
            reminders = reminders.stream()
                .filter(r -> r.getDateTime().toLocalDate().isEqual(date))
                .collect(Collectors.toList());
        }

        
        if (treatment != null) {
            reminders = reminders.stream()
                .filter(r -> r.getTreatment().getName().equalsIgnoreCase(treatment))
                .collect(Collectors.toList());
        }
          return generatePdf(reminders);
    }
}
