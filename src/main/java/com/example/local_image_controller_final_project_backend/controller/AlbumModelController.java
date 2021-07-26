package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.model.AlbumModel;
import com.example.local_image_controller_final_project_backend.service.AlbumModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumModelController {
    private final AlbumModelService albumModelService;

    public AlbumModelController(AlbumModelService albumModelService) {
        this.albumModelService = albumModelService;
    }
    @GetMapping
    public List<AlbumModel> getAllAlbums(){
      return albumModelService.getAllAlbums();
    }

    @PostMapping("/createAlbum")
    public void saveAlbumModelToDB (@RequestBody AlbumModel albumModel){
        System.out.println(albumModel.toString());
        albumModelService.saveAlbumModelToDB(albumModel);
    }

}
