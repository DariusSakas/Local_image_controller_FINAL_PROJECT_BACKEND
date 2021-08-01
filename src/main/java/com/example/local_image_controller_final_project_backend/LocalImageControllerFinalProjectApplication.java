package com.example.local_image_controller_final_project_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class LocalImageControllerFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalImageControllerFinalProjectApplication.class, args);
    }

}
