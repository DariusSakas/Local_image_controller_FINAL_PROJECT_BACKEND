package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.model.ImageModel;
import com.example.local_image_controller_final_project_backend.repository.AlbumModelRepository;
import com.example.local_image_controller_final_project_backend.service.ImageModelService;
import com.example.local_image_controller_final_project_backend.service.ImageStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ImageModelController(ImageModelService imageModelService, ImageStorageService imageStorageService, AlbumModelRepository albumModelrepository) {
        this.imageModelService = imageModelService;
        this.imageStorageService = imageStorageService;
    }

    /**
     * API will request MultiPartFile 'imageFile' and String 'imageModelJSON' params with image file from client.
     * Using Jackson library, JSON String is converted into ImageModel object and
     * sent for imageModelService Service layer to save Object to DB.
     */

    @GetMapping
    public ResponseEntity<List<ImageModel>> getAllImagesData() {
        return new ResponseEntity<>(imageModelService.findAllImagesData(), HttpStatus.OK);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("imageModelJSON") String imageModelJSON) {

        //Drop to separate method here??????????
        ObjectMapper objectMapper = new ObjectMapper();

//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(ImageModel.class, new FullImageModelJSONDeserializer());
//        objectMapper.registerModule(module);

        try {
            ImageModel imageModel = objectMapper.readValue(imageModelJSON, ImageModel.class);

            imageModel.setImageFileStorageLocation(imageStorageService.saveImageToLocalStorage(imageFile, IMAGES_STORAGE_PATH, imageModel));
            imageModel.setImageThumbnailFileStorageLocation(imageStorageService.createThumbnailImage(IMAGES_STORAGE_PATH, THUMBNAIL_STORAGE_PATH));

            System.out.println(imageModelJSON);
            System.out.println(imageModel);

            imageModelService.saveImageDataToDB(imageModel);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Image and thumbnail upload failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Image posted and thumbnail generated successfully", HttpStatus.OK);
    }

    @PutMapping("/updateImage")
    public ResponseEntity<String> updateImageData(@RequestBody ImageModel imageModel)  {

        imageModelService.saveImageDataToDB(imageModel);
        return new ResponseEntity<>("Image data updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable(name = "imageId") Long imageId){
        imageModelService.deleteImageFromDB(imageId);

        return new ResponseEntity<>("Image removed", HttpStatus.OK);
    }
}
