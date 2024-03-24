package com.crio.eventmanagement.service;

import com.crio.eventmanagement.model.Event;

public interface MailService {
    void sendMail(String email, String name, Event event);
}
