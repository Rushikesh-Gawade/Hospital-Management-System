package com.myco.hospital_management.controllers;

// Spring Framework Core Annotations
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

//Spring Data Support
import org.springframework.data.domain.Page;

// Models and Services
import com.myco.hospital_management.models.Patient;
import com.myco.hospital_management.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired    //dependcy injection
    private PatientService patientServiceobj;
   
    @GetMapping   //Patient entity from models
    public Page<Patient> getAllPatients(@RequestParam(defaultValue = "0") int page, //pageantion values passed
                                        @RequestParam(defaultValue = "2") int size){  
        System.out.println("Fetching the patients");
        
        return patientServiceobj.getAllPatients(page, size);  //code goes to service layer 
    }                                                         //retrive lis of all the patients

    @PostMapping
    public Patient createPatient(@RequestBody Patient patientobj){  //Patient entity from models objct = patientobj
            System.out.println("Creating patient");               //The first Patient (after public) 
            return patientServiceobj.createPatient(patientobj);     //is the return type of the method.
    }

    @GetMapping("/{id}")  //Here,{id} is a path variable – a placeholder that Spring Boot extracts from the URL.
    public Patient getPatientById(@PathVariable Long id){
        System.out.println("Fetching id by ID");
        
        return patientServiceobj.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        patientServiceobj.deletePatient(id);
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        System.out.println("Updating id by ID");  
        patientServiceobj.updatePatient(id, patient);
    }
}
