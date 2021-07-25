package com.example.local_image_controller_final_project_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

    public void setImageFileStorageLocation(String imageFileStorageLocation) {
        this.imageFileStorageLocation = imageFileStorageLocation;
    }

    public void setImageThumbnailFileStorageLocation(String imageThumbnailFileStorageLocation) {
        this.imageThumbnailFileStorageLocation = imageThumbnailFileStorageLocation;
    }





}
