package com.myco.hospital_management.service;

// Spring Boot and Spring Data imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Model and Repository imports
import com.myco.hospital_management.models.Appointment;
import com.myco.hospital_management.repository.AppointmentRepository;

import java.util.HashMap;
// Java utility
import java.util.Optional;
import java.util.Map;


@Service
public class AppointmentService {
   private final String webhookUrl = "https://webhook.site/53357a71-dcbb-470d-b713-e3f7b175c935";

   @Autowired
   private MailService mailServiceobj;

   @Autowired
   private RestTemplate restTemplate;
 

  @Autowired
  private AppointmentRepository appointmentRepositoryobj;

    public Page<Appointment>getAllAppointments(int page, int size){
        try{
            System.out.println("into service layer");
             Pageable pageable = PageRequest.of(page, size);
            return appointmentRepositoryobj.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
        }
    }


public Appointment getAppointmentById(Long id) {
    try{
        Optional<Appointment> appointment = appointmentRepositoryobj.findById(id);
        return appointment.orElse(null);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

    public Appointment createAppointment(Appointment appointment) {
    try{
       appointmentRepositoryobj.save(appointment);
        Map<String, String> emailPayload = new HashMap<>();
        emailPayload.put("to", appointment.getEmail());
        emailPayload.put("subject", "Appointment Confirmation");
        emailPayload.put("body", "Dear Patient,\n\nYour appointment has been successfully created.\n" +
                                 "Reason: " + appointment.getReason() + "\n" +
                                 "Doctor ID: " + appointment.getDoctorId() + "\n" +
                                 "Patient ID: " + appointment.getPatientId() + "\n\n" +
                                 "Thank you!");

        // Call mail service
        mailServiceobj.sendMail(emailPayload);

         sendWebhook(appointment);

        return appointment;
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}
//  Send Webhook Method
    private void sendWebhook(Appointment appointment) {
        try {
            Map<String, Object> webhookPayload = new HashMap<>();
            webhookPayload.put("id", appointment.getId());
            webhookPayload.put("patientId", appointment.getPatientId());
            webhookPayload.put("doctorId", appointment.getDoctorId());
            webhookPayload.put("reason", appointment.getReason());
            webhookPayload.put("email", appointment.getEmail());
            webhookPayload.put("message", "Appointment created successfully!");

            restTemplate.postForObject(webhookUrl, webhookPayload, String.class);

            System.out.println("Webhook sent successfully!");
        } catch (Exception e) {
            System.out.println("Error sending webhook: " + e.getMessage());
        }
    }

public void deleteAppointment(Long id) {
    try{
       appointmentRepositoryobj.deleteById(id);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            
    }
}

public Appointment updateAppointment(Long id, Appointment updateAppointment) {
    try{
       Optional<Appointment> existingAppointment = appointmentRepositoryobj.findById(id); // Checks if patient exist
        if(existingAppointment.isPresent()){
            Appointment a = existingAppointment.get();
            a.setReason(updateAppointment.getReason());
            return appointmentRepositoryobj.save(a);
        }else{
            System.out.println("No patient found");
            return null;
        }

    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}
}
