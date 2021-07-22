package com.example.local_image_controller_final_project_backend.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface LocalStorageComponent {

    void saveImageToLocalStorage(MultipartFile imageFile, String imagesStoragePath) throws Exception;
    void createThumbnailImage(String imagesStoragePath, String thumbnailStoragePath) throws Exception;
}
