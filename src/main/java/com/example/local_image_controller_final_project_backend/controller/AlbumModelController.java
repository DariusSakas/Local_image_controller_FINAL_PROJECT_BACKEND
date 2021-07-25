package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.service.AlbumModelService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumModelController {
    private final AlbumModelService albumModelService;

    public AlbumModelController(AlbumModelService albumModelService) {
        this.albumModelService = albumModelService;
    }


}
