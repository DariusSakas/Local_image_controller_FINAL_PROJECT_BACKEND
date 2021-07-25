package com.example.local_image_controller_final_project_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "image_model")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_model_id")
    private Long id;
    private String imageFileStorageLocation;
    private String imageThumbnailFileStorageLocation;
    private String imageDescription;
    private String locationWhereImageWasTaken;
    private String dateOfTakenImage;
    /**
     * The @ManyToOne side is always the Child association since it maps the underlying Foreign Key column.
     * Use Cascade type only on parent, so when child is removed, parents is being kept.
     */

    @ManyToOne
    @JoinColumn(name = "album_model_id", referencedColumnName = "album_model_id", insertable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private AlbumModel albumModel;

    public void setImageFileStorageLocation(String imageFileStorageLocation) {
        this.imageFileStorageLocation = imageFileStorageLocation;
    }

    public void setImageThumbnailFileStorageLocation(String imageThumbnailFileStorageLocation) {
        this.imageThumbnailFileStorageLocation = imageThumbnailFileStorageLocation;
    }

    public ImageModel() {
    }

    public ImageModel(AlbumModel albumModel) {
        this.albumModel = albumModel;
    }

    public Long getId() {
        return id;
    }

    public String getImageFileStorageLocation() {
        return imageFileStorageLocation;
    }

    public String getImageThumbnailFileStorageLocation() {
        return imageThumbnailFileStorageLocation;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public String getLocationWhereImageWasTaken() {
        return locationWhereImageWasTaken;
    }

    public String getDateOfTakenImage() {
        return dateOfTakenImage;
    }

    public AlbumModel getAlbumModel() {
        return albumModel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void setLocationWhereImageWasTaken(String locationWhereImageWasTaken) {
        this.locationWhereImageWasTaken = locationWhereImageWasTaken;
    }

    public void setDateOfTakenImage(String dateOfTakenImage) {
        this.dateOfTakenImage = dateOfTakenImage;
    }

    public void setAlbumModel(AlbumModel albumModel) {
        this.albumModel = albumModel;
    }
}
