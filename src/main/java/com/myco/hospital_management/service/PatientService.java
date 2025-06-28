package com.myco.hospital_management.service;


// Spring Boot and Spring Data imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

// Model and Repository imports
import com.myco.hospital_management.models.Patient;
import com.myco.hospital_management.repository.PatientRepository;

// Java utility
import java.util.Optional;

@Service
 public class PatientService {

  @Autowired
  private PatientRepository patientRepositoryobj; //dependncy injection

    public Page<Patient>getAllPatients(int page, int size){//interact with repository layer
        try{
            System.out.println("Into service layer");
            Pageable pageable = PageRequest.of(page, size);
            return patientRepositoryobj.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
        }
    }


public Patient getPatientById(Long id) {
    try{
        Optional<Patient> patient = patientRepositoryobj.findById(id);
        return patient.orElse(null); //if got details or return null
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

    public Patient createPatient(Patient patient) {
    try{
        patientRepositoryobj.save(patient);
        return patient;
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

public void  deletePatient(Long id) {
    try{
       patientRepositoryobj.deleteById(id);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            
    }
}

public Patient updatePatient(Long id, Patient updatePatient) {
    try{
        Optional<Patient> existingPatient = patientRepositoryobj.findById(id); // Checks if patient exist
        if(existingPatient.isPresent()){
            Patient p = existingPatient.get();
            p.setName(updatePatient.getName());
            p.setAge(updatePatient.getAge());
            p.setGender(updatePatient.getGender());

             return patientRepositoryobj.save(p); 
        }else{
            System.out.println("No patient found with this id");
        }
        return null;
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}
}
