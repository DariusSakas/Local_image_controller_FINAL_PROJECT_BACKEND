package com.example.local_image_controller_final_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class LocalImageControllerFinalProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalImageControllerFinalProjectBackendApplication.class, args);
    }

}
