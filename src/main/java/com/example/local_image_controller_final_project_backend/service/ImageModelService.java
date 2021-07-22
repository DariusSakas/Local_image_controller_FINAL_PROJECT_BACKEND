package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.repository.ImageModelRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageModelService  {
    /**ImageModelService responsible for CRUD operations with data about image files
    */
    private final ImageModelRepository imageModelRepository;

    public ImageModelService(ImageModelRepository imageModelRepository) {
        this.imageModelRepository = imageModelRepository;
    }


    public void saveImageDataToDB() {

    }
}
