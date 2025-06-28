package com.myco.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myco.hospital_management.models.Patient;

@Repository                                                     //Entity , Type of Primary key
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
