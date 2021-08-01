package com.example.local_image_controller_final_project_backend.model;

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
     * insertable = false, updatable = false ???????
     */
    @ManyToOne
    @JoinColumn(name = "album_model_id", referencedColumnName = "album_model_id")
    private AlbumModel albumModel;

    //ALL-ARGS:

    public ImageModel(Long id, String imageFileStorageLocation, String imageThumbnailFileStorageLocation, String imageDescription, String locationWhereImageWasTaken, String dateOfTakenImage, AlbumModel albumModel) {
        this.id = id;
        this.imageFileStorageLocation = imageFileStorageLocation;
        this.imageThumbnailFileStorageLocation = imageThumbnailFileStorageLocation;
        this.imageDescription = imageDescription;
        this.locationWhereImageWasTaken = locationWhereImageWasTaken;
        this.dateOfTakenImage = dateOfTakenImage;
        this.albumModel = albumModel;
    }

    //NO-ARGS:

    public ImageModel() {
    }

    public ImageModel(Long id) {
        this.id = id;
    }

    //GETTERS:

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

    //SETTERS:

    public void setImageFileStorageLocation(String imageFileStorageLocation) {
        this.imageFileStorageLocation = imageFileStorageLocation;
    }

    public void setImageThumbnailFileStorageLocation(String imageThumbnailFileStorageLocation) {
        this.imageThumbnailFileStorageLocation = imageThumbnailFileStorageLocation;
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

    //TO-STRING:

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", imageFileStorageLocation='" + imageFileStorageLocation + '\'' +
                ", imageThumbnailFileStorageLocation='" + imageThumbnailFileStorageLocation + '\'' +
                ", imageDescription='" + imageDescription + '\'' +
                ", locationWhereImageWasTaken='" + locationWhereImageWasTaken + '\'' +
                ", dateOfTakenImage='" + dateOfTakenImage + '\'' +
                ", albumModel=" + albumModel +
                '}';
    }
}
