package com.example.local_image_controller_final_project_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("frontend/public/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("frontend/public");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("frontend/public");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("frontend/public");
        registry.addResourceHandler("/index.html")
                .addResourceLocations("frontend/public/index.html");
    }
}
