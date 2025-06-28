package com.myco.hospital_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myco.hospital_management.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
