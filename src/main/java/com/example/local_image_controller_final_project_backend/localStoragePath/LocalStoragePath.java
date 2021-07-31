package com.example.local_image_controller_final_project_backend.localStoragePath;

public class LocalStoragePath {

    private static final String THUMBNAIL_STORAGE_PATH = "src/main/resources/thumbnails";
    private static final String IMAGES_STORAGE_PATH = "src/main/resources/images/";

    public static String getThumbnailStoragePath() {
        return THUMBNAIL_STORAGE_PATH;
    }

    public static String getImagesStoragePath() {
        return IMAGES_STORAGE_PATH;
    }
}
