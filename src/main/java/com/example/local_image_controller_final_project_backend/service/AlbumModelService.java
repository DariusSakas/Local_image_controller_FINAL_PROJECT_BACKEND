package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.model.AlbumModel;
import com.example.local_image_controller_final_project_backend.repository.AlbumModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumModelService {
    private final AlbumModelRepository albumModelrepository;

    public AlbumModelService(AlbumModelRepository albumModelrepository) {
        this.albumModelrepository = albumModelrepository;
    }

    public void saveAlbumModelToDB(AlbumModel albumModel){
        albumModelrepository.save(albumModel);
    }

    public List<AlbumModel> getAllAlbums() {
        return albumModelrepository.findAll();
    }
}
