package com.myco.hospital_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSenderobj;

     public void sendMail(Map<String, String> emailPayload) {
        try {
            String toEmail = emailPayload.get("to");
            String subject = emailPayload.get("subject");
            String body = emailPayload.get("body");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("rushigawade69@gmail.com");

            mailSenderobj.send(message);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
}
}