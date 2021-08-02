package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.exceptions.UnableToCreateAlbumModel;
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

    public void saveAlbumModelToDB(AlbumModel albumModel) throws UnableToCreateAlbumModel {
        try {
            albumModelrepository.save(albumModel);
        }catch (Exception e){
            throw new UnableToCreateAlbumModel("Album not saved. Error");
        }
    }

    public List<AlbumModel> getAllAlbums() {
        return albumModelrepository.findAll();
    }

    public void updateAlbum(AlbumModel albumModel) {
        albumModelrepository.save(albumModel);
    }

    public void deleteAlbum(Long albumId) {
        albumModelrepository.deleteById(albumId);
    }
}
