package com.example.local_image_controller_final_project_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album_model")
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_model_id")
    private Long id;
    private String albumName;
    /**
     * Mapped by reference goes for variable name, not column name
     * FetchType.Lazy - loads imageModel only by demand when fetching albumModel data
     * CascadeType.All - will propagate (cascade) all EntityManager operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH) to the relating entities.
     * *Persist - remain something*
     */

    @OneToMany(targetEntity = ImageModel.class, mappedBy = "albumModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ImageModel> imageModel;

    //NO-IMAGE-ARG:

    public AlbumModel(Long id, String albumName) {
        this.id = id;
        this.albumName = albumName;
    }

    //ALL-ARGS:

    public AlbumModel(Long id, String albumName, List<ImageModel> imageModel) {
        this.id = id;
        this.albumName = albumName;
        this.imageModel = imageModel;
    }

    //NO-ARGS:

    public AlbumModel() {
    }

    //GETTERS:

    public Long getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public List<ImageModel> getImageModel() {
        return imageModel;
    }

    //SETTERS:

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setImageModel(List<ImageModel> imageModel) {
        this.imageModel = imageModel;
    }

    //TO-STRING:


    @Override
    public String toString() {
        return "AlbumModel{" +
                "id=" + id +
                ", albumName='" + albumName + '\'' +
                ", imageModel=" + imageModel +
                '}';
    }
}
