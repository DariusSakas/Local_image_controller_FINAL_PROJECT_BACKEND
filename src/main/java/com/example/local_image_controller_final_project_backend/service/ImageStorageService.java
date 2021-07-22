package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.component.LocalStorageComponent;
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

    private String currentFileName;

    @Override
    public void saveImageToLocalStorage(MultipartFile imageFile, String imagesStoragePath) throws Exception {

        byte[] imageBytes = imageFile.getBytes();
        Path imagePath = Paths.get(imagesStoragePath + imageFile.getOriginalFilename());

        currentFileName = Files.write(imagePath, imageBytes).getFileName().toString();
        System.out.println("Current file name: " +currentFileName);
    }

    @Override
    public void createThumbnailImage(String imagesStoragePath, String thumbnailStoragePath) throws Exception {
        File newThumbnailFile = new File(thumbnailStoragePath);
        Thumbnails.of(String.format("%s/%s", imagesStoragePath, currentFileName)).size(480,480).toFiles(newThumbnailFile, Rename.PREFIX_HYPHEN_THUMBNAIL);
        System.out.printf("Created new thumbnail file at %s with name %s", thumbnailStoragePath, newThumbnailFile.getName());
    }
}
