package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.component.LocalStorageComponent;
import com.example.local_image_controller_final_project_backend.model.ImageModel;
import com.example.local_image_controller_final_project_backend.repository.ImageModelRepository;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageService implements LocalStorageComponent {
    /**
     * ImageStorageService responsible for CRUD operations image and thumbnail files stored locally
     */
    private final ImageModelRepository imageModelRepository;

    private static String currentFileName;

    public ImageStorageService(ImageModelRepository imageModelRepository) {
        this.imageModelRepository = imageModelRepository;
    }

    /**
     * saveImageToLocalStorage method coverts uploaded image into bytes and saves it at chosen path + name
     * returns String path to that image
     */
    @Override
    public String saveImageToLocalStorage(MultipartFile imageFile, String imagesStoragePath, ImageModel imageModel) throws Exception {
//        String generatedImageName = generateImageName(imageModel);

        byte[] imageBytes = imageFile.getBytes();
        Path imagePath = Paths.get(imagesStoragePath + imageFile.getOriginalFilename());

        currentFileName = Files.write(imagePath, imageBytes).getFileName().toString();
        System.out.println("Current file name: " + imagesStoragePath + currentFileName);

        return imagesStoragePath + currentFileName;
    }

//    private String generateImageName(ImageModel imageModel) {
//
//        return String.format("%d_%s_%s_%d",
//                ofNullable(imageModel.getId()).orElse(1L),
//                ofNullable(imageModel.getDateOfTakenImage()).orElse(""),
//                ofNullable(imageModel.getLocationWhereImageWasTaken()).orElse(""),
//                ofNullable(imageModel.getAlbumModel().getId()).orElse(1L)
//        );
//    }

    /**
     * createThumbnailImage method uses Thumblinator (Thumbnails.of()) to convert uploaded image file into small size thumbnail
     * returns String path to that thumbnail
     */

    @Override
    public String createThumbnailImage(String imagesStoragePath, String thumbnailStoragePath) throws Exception {
        File newThumbnailFile = new File(thumbnailStoragePath);

        Thumbnails.of(String.format("%s/%s", imagesStoragePath, currentFileName)).size(250, 250).toFiles(newThumbnailFile, Rename.PREFIX_HYPHEN_THUMBNAIL);
        String thumbnailFileLocation = thumbnailStoragePath + "/thumbnail-" + currentFileName;

        System.out.println("Created new thumbnail file at: " + thumbnailFileLocation);

        return thumbnailFileLocation;
    }
}
