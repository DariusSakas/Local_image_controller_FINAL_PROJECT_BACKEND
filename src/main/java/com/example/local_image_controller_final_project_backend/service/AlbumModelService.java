package com.example.local_image_controller_final_project_backend.service;

import com.example.local_image_controller_final_project_backend.repository.AlbumModelrepository;
import org.springframework.stereotype.Service;

@Service
public class AlbumModelService {
    private final AlbumModelrepository albumModelrepository;

    public AlbumModelService(AlbumModelrepository albumModelrepository) {
        this.albumModelrepository = albumModelrepository;
    }
}
