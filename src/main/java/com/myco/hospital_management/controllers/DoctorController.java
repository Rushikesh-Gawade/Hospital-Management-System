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
import com.myco.hospital_management.models.Doctor;
import com.myco.hospital_management.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
  @Autowired
  private DoctorService doctorServiceobj;  

    @GetMapping
    public Page<Doctor> getAllDoctors(@RequestParam(defaultValue = "0") int page, 
                                      @RequestParam(defaultValue = "2") int size){
        System.out.println("Fetching all doctor details");
        
        return doctorServiceobj.getAllDoctors(page, size);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctorobj){
         System.out.println("Creating doctor details");

         return doctorServiceobj.createDoctor(doctorobj);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
        System.out.println("Fetching id by ID");

        return doctorServiceobj.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id){
      doctorServiceobj.deleteDoctor(id);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        System.out.println("Updating id by ID"); 
        return doctorServiceobj.updateDoctor(id, doctor);
    }
}
