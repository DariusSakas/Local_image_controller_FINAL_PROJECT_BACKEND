package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.model.ImageModel;
import com.example.local_image_controller_final_project_backend.service.ImageModelService;
import com.example.local_image_controller_final_project_backend.service.ImageStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageModelController {

    //Drop to service layer????
    private static final String THUMBNAIL_STORAGE_PATH = "src/main/resources/thumbnails";
    private static final String IMAGES_STORAGE_PATH = "src/main/resources/images/";

    private final ImageModelService imageModelService;
    private final ImageStorageService imageStorageService;

    public ImageModelController(ImageModelService imageModelService, ImageStorageService imageStorageService) {
        this.imageModelService = imageModelService;
        this.imageStorageService = imageStorageService;
    }

    /**
     * API will request 'imageFile' param with image file from client.
     */
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile")MultipartFile imageFile, @RequestParam("imageModelJSON")String imageModelJSON){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ImageModel imageModel = objectMapper.readValue(imageModelJSON, ImageModel.class);

            imageStorageService.saveImageToLocalStorage(imageFile, IMAGES_STORAGE_PATH);
            imageStorageService.createThumbnailImage(IMAGES_STORAGE_PATH, THUMBNAIL_STORAGE_PATH);

            imageModelService.saveImageDataToDB(imageModel);
            //Common or seperate unique exceptions????
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Image and thumbnail upload failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Image posted and thumbnail generated successfully", HttpStatus.OK);
    }

    @GetMapping
    public List<ImageModel> getAllImagesData(){
        return imageModelService.findAllImagesData();
    }

}
