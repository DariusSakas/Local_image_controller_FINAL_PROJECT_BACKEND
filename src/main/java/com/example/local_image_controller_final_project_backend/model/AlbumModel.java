package com.example.local_image_controller_final_project_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @JsonIgnore
    @OneToMany(targetEntity = ImageModel.class, mappedBy = "albumModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageModel> imageModel;

    public AlbumModel(Long id, String albumName) {
        this.id = id;
        this.albumName = albumName;
    }

}
