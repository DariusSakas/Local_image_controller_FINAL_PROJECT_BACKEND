package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.exceptions.ModelDataNotFound;
import com.example.local_image_controller_final_project_backend.exceptions.UnableToSaveModelDataToDB;
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

    public void saveAlbumModelToDB(AlbumModel albumModel) throws UnableToSaveModelDataToDB {
        try {
            albumModelrepository.save(albumModel);
        }catch (Exception e){
            throw new UnableToSaveModelDataToDB("Album not saved. Error");
        }
    }

    public List<AlbumModel> getAllAlbums() {
        return albumModelrepository.findAll();
    }

    public void updateAlbum(AlbumModel albumModel) throws ModelDataNotFound {
        AlbumModel albumModelToSave = albumModelrepository.findById(albumModel.getId()).orElse(null);
        if (albumModelToSave == null) {
            throw new ModelDataNotFound("Cannot find such album data in DB");
        }
        albumModelrepository.save(albumModel);
    }

    public void deleteAlbum(Long albumId) {
        albumModelrepository.deleteById(albumId);
    }
}
