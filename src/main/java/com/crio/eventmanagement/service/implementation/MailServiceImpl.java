package com.crio.eventmanagement.service.implementation;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.crio.eventmanagement.model.Event;
import com.crio.eventmanagement.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    // private static final String FROM_MAIL = "pythontesterdev01@gmail.com";

    @Value("${spring.mail.username}")
    private String fromMail;

    private static final String SUBJECT = "EVENTS API - Registraion Successful";
    private static final String BODY_TEMPLATE = """
            Hi %s,
            
            We hope this email finds you well.

            We are sending this email as a confirmation of your event registration through EVENTS API. Please find the event details below.

            ************
            Event Name: %s

            Event Description: %s

            Event Place: %s

            Event Date: %s

            Event Time: %s
            ************

            Looking forward to seeing you.
            
            Best Regards,
            EVENTS API
            """;

    @Override
    public void sendMail(String email, String name, Event event) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        String eventDate = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH).format(event.getEventDate());
        String eventTime = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH).format(event.getEventTime());


        String body = String.format(BODY_TEMPLATE, name, event.getEventName(), event.getEventDescription(),
                event.getEventPlace(), eventDate, eventTime);

        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(body);
        simpleMailMessage.setTo(email);

        mailSender.send(simpleMailMessage);
    }

}
