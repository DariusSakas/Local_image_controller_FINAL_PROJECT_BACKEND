package com.example.local_image_controller_final_project_backend.component;

import com.example.local_image_controller_final_project_backend.model.ImageModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface LocalStorageComponent {

    String saveImageToLocalStorage(MultipartFile imageFile, String imagesStoragePath, ImageModel imageModel) throws Exception;
    String createThumbnailImage(String imagesStoragePath, String thumbnailStoragePath) throws Exception;

}
