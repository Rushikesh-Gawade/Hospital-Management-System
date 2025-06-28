package com.myco.hospital_management.service;

// Spring Boot and Spring Data imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Model and Repository imports
import com.myco.hospital_management.models.Doctor;
import com.myco.hospital_management.repository.DoctorRepository;

// Java utility
import java.util.Optional;


@Service
public class DoctorService {

  @Autowired
  private DoctorRepository doctorRepositoryobj;
  
    public Page<Doctor>getAllDoctors(int page, int size){
        try{
            System.out.println("Into service layer");
              Pageable pageable = PageRequest.of(page, size);
            return doctorRepositoryobj.findAll(pageable);
        }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
        }
    }


public Doctor getDoctorById(Long id) {
    try{
        Optional<Doctor> doctor = doctorRepositoryobj.findById(id);
        return doctor.orElse(null);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

    public Doctor createDoctor(Doctor doctor) {
    try{
        doctorRepositoryobj.save(doctor);
        return doctor;
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}

public void deleteDoctor(Long id) {
    try{
       doctorRepositoryobj.deleteById(id);
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            
    }
}

public Doctor updateDoctor(Long id, Doctor updateDoctor) {
    try{
       Optional<Doctor> existingDoctor = doctorRepositoryobj.findById(id); // Checks if patient exist
        if(existingDoctor.isPresent()){
            Doctor d = existingDoctor.get();
            d.setName(updateDoctor.getName());
            d.setSpecialization(updateDoctor.getSpecialization());
            d.setContactNumber(updateDoctor.getContactNumber());

           return doctorRepositoryobj.save(d);
        }else{
            System.out.println("No Doctor found");
            return null;
        }
    }catch(Exception e){
            System.out.println("Error message:" + e.getMessage());
            return null;
    }
}
}
