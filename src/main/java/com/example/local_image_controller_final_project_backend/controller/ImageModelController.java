package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.model.AlbumModel;
import com.example.local_image_controller_final_project_backend.model.ImageModel;
import com.example.local_image_controller_final_project_backend.repository.AlbumModelRepository;
import com.example.local_image_controller_final_project_backend.serializer.CustomImageModelJSONDeserializer;
import com.example.local_image_controller_final_project_backend.service.ImageModelService;
import com.example.local_image_controller_final_project_backend.service.ImageStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageModelController {

    //Drop to service layer???????????????????????????????
    private static final String THUMBNAIL_STORAGE_PATH = "src/main/resources/thumbnails";
    private static final String IMAGES_STORAGE_PATH = "src/main/resources/images/";

    private final ImageModelService imageModelService;
    private final ImageStorageService imageStorageService;
    private final AlbumModelRepository albumModelrepository;

    public ImageModelController(ImageModelService imageModelService, ImageStorageService imageStorageService, AlbumModelRepository albumModelrepository) {
        this.imageModelService = imageModelService;
        this.imageStorageService = imageStorageService;
        this.albumModelrepository = albumModelrepository;
    }

    /**
     * API will request MultiPartFile 'imageFile' and String 'imageModelJSON' params with image file from client.
     * Using Jackson library, JSON String is converted into ImageModel object and
     * sent for imageModelService Service layer to save Object to DB.
     *
     * TODO:
     * 1. Autogenerate unique reference name +1 by adding same
     * 2. Don't allow to upload image if Album doesn't exist
     *
     */
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("imageModelJSON") String imageModelJSON) {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ImageModel.class, new CustomImageModelJSONDeserializer());
        objectMapper.registerModule(module);

        try {

            ImageModel imageModel = objectMapper.readValue(imageModelJSON, ImageModel.class);

            imageModel.setImageFileStorageLocation(imageStorageService.saveImageToLocalStorage(imageFile, IMAGES_STORAGE_PATH, imageModel));
            imageModel.setImageThumbnailFileStorageLocation(imageStorageService.createThumbnailImage(IMAGES_STORAGE_PATH, THUMBNAIL_STORAGE_PATH));

            System.out.println(imageModelJSON);
            System.out.println(imageModel);

            imageModelService.saveImageDataToDB(imageModel);

            //Common or seperate unique exceptions????????????????????????????
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Image and thumbnail upload failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Image posted and thumbnail generated successfully", HttpStatus.OK);
    }

    @GetMapping
    public List<ImageModel> getAllImagesData() {
        return imageModelService.findAllImagesData();
    }

}
