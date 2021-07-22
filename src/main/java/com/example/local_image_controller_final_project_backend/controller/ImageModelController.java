package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.service.ImageModelService;
import com.example.local_image_controller_final_project_backend.service.ImageStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageModelController {

    private static final String THUMBNAIL_STORAGE_PATH = "src/main/resources/thumbnails";
    private static final String IMAGES_STORAGE_PATH = "src/main/resources/images/";

    private final ImageModelService imageModelService;
    private final ImageStorageService imageStorageService;

    public ImageModelController(ImageModelService imageModelService, ImageStorageService imageStorageService) {
        this.imageModelService = imageModelService;
        this.imageStorageService = imageStorageService;
    }

    //API will request 'imageFile' param with image file from client.
    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile")MultipartFile imageFile){
        String returnValue = "start";
        try {
            imageStorageService.saveImageToLocalStorage(imageFile, IMAGES_STORAGE_PATH);
            imageStorageService.createThumbnailImage(IMAGES_STORAGE_PATH, THUMBNAIL_STORAGE_PATH);
            //Common or seperate unique exceptions????
        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "error";
        }


        return returnValue;
    }

}
