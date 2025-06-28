package com.myco.hospital_management.controllers;

// Spring Framework Core Annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Spring Data Support
import org.springframework.data.domain.Page;

// Models and Services
import com.myco.hospital_management.models.Appointment;
import com.myco.hospital_management.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
  
    @Autowired
    AppointmentService appointmentServiceobj;

    @GetMapping
    public Page<Appointment> getAllAppointments(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "2") int size){
          System.out.println("Fetching the Appointments");

          return appointmentServiceobj.getAllAppointments(page, size);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointmentobj){
             System.out.println("Creating  Appointment");

             return appointmentServiceobj.createAppointment(appointmentobj);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
         System.out.println("Fetching id by ID");

         return appointmentServiceobj.getAppointmentById(id);
    } 

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id){
        appointmentServiceobj.deleteAppointment(id);
    }

    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        System.out.println("Updating id by ID"); 
        appointmentServiceobj.updateAppointment(id, appointment);
    }

}
