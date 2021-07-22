package com.example.local_image_controller_final_project_backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String generatedImageFileName;
//    private String imageFileLocation;
//    private String imageThumbnailFileLocation;
//    private String imageDescription;
//    private String imageLocationWhereWasTaken;
//    private String dateOfTakenImage;
//    private String imageTags;


    public ImageModel() {
    }

    public ImageModel(Long id, String generatedImageFileName) {
        this.id = id;
        this.generatedImageFileName = generatedImageFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeneratedImageFileName() {
        return generatedImageFileName;
    }

    public void setGeneratedImageFileName(String generatedImageFileName) {
        this.generatedImageFileName = generatedImageFileName;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", generatedImageFileName='" + generatedImageFileName + '\'' +
                '}';
    }
}
