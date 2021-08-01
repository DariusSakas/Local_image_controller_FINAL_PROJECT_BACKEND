package com.example.local_image_controller_final_project_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class LocalStoragePath implements CommandLineRunner {

    private static String imageStoragePath = "";
    private static String thumbnailStoragePath = "";

    public static String getThumbnailStoragePath() {
        return thumbnailStoragePath;
    }

    public static String getImagesStoragePath() {
        return imageStoragePath;
    }

    /**
     * Method will autogenerate full path to "images" and "thumbnails" folders
     */
    @Override
    public void run(String... args) throws Exception {
        File imagesPath = new File("src/main/resources/images/");
        File thumbnails = new File("src/main/resources/thumbnails/");

        imageStoragePath = imagesPath.toURI().getPath();
        thumbnailStoragePath = thumbnails.toURI().getPath();

        imageStoragePath = imageStoragePath.substring(1);
        thumbnailStoragePath = thumbnailStoragePath.substring(1);

        System.out.println("***************************************************");
        System.out.println("Images are stored at: " + imageStoragePath);
        System.out.println("Thumbnails are stored at: "+ thumbnailStoragePath);
    }
}
