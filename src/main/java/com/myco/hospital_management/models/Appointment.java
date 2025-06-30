package com.myco.hospital_management.models;

// JPA Annotations
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appointment {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private long patientId;
      private long doctorId;
      private String reason;
      private String email;

      //Getter and  stter for id,patientId,doctorId,reason
      public Long getId() {
          return id;
      }
      public void setId(Long id) {
          this.id = id;
      }
      public long getPatientId() {
          return patientId;
      }
      public void setPatientId(long patientId) {
          this.patientId = patientId;
      }
      public long getDoctorId() {
          return doctorId;
      }
      public void setDoctorId(long doctorId) {
          this.doctorId = doctorId;
      }
      public String getReason() {
          return reason;
      }
      public void setReason(String reason) {
          this.reason = reason;
      }
       public String getEmail() {
        return email;
      }
    public void setEmail(String email) {
        this.email = email;
      }
}
