package com.myco.hospital_management.service;

// Spring Boot and Spring Data imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Model and Repository imports
import com.myco.hospital_management.models.Appointment;
import com.myco.hospital_management.repository.AppointmentRepository;

// Java utility
import java.util.Optional;


@Service
public class AppointmentService {

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
       return appointment;
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
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
