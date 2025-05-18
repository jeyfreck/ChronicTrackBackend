package com.chronicktrack.chronictrack.service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chronicktrack.chronictrack.entity.Reminder;
import com.chronicktrack.chronictrack.entity.Treatment;
import com.chronicktrack.chronictrack.repository.ReminderRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ReminderService {

private final ReminderRepository reminderRepository;

public ReminderService(ReminderRepository reminderRepository){
this.reminderRepository = reminderRepository;
}

public Reminder createReminder(String message, LocalDateTime dateTime, Treatment treatment){
return reminderRepository.save(new Reminder(dateTime, message, treatment));
}

public List<Reminder> getRemindersForTreatment(Treatment treatment){
return reminderRepository.findByTreatment(treatment);
}

private byte[] generatePdf(List<Reminder> reminders) {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try {
        PdfWriter.getInstance(document, out);
        document.open();

        // Add a title to the PDF
        document.add(new Paragraph("Reminders List"));

        // Add each reminder to the PDF
        for (Reminder reminder : reminders) {
            document.add(new Paragraph(String.format("ID: %d, Message: %s, DateTime: %s",
                    reminder.getId(),
                    reminder.getMessage(),
                    reminder.getDateTime().toString())));
        }

        document.close();
    } catch (DocumentException e) {
        e.printStackTrace(); // Log the exception or handle it appropriately
    }
    
    return out.toByteArray();
}
}
