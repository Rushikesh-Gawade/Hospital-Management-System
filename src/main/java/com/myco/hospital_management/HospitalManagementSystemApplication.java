package com.myco.hospital_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//project is running on port 8081
@SpringBootApplication(scanBasePackages = {
    "com.myco.hospital_management.controllers",
    "com.myco.hospital_management.service",
    "com.myco.hospital_management.repository"
})
@EntityScan(basePackages = "com.myco.hospital_management.models")
@RestController
public class HospitalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}
    
     @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
