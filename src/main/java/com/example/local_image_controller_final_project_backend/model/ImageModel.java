package com.example.local_image_controller_final_project_backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ImageModel {

    @Id
    private String generatedImageFileName;
    private String imageFileLocation;
    private String imageThumbnailFileLocation;
    private String imageDescription;
    private String imageLocationWhereWasTaken;
    private String dateOfTakenImage;
    private String imageTags;

}
