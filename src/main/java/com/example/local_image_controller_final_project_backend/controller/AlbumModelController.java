package com.example.local_image_controller_final_project_backend.controller;

import com.example.local_image_controller_final_project_backend.exceptions.ModelDataNotFound;
import com.example.local_image_controller_final_project_backend.exceptions.UnableToSaveModelDataToDB;
import com.example.local_image_controller_final_project_backend.model.AlbumModel;
import com.example.local_image_controller_final_project_backend.service.AlbumModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AlbumModel>> getAllAlbums(){
      return new ResponseEntity<>(albumModelService.getAllAlbums(), HttpStatus.OK);
    }

    @PostMapping("/createAlbum")
    public ResponseEntity<String> saveAlbumModelToDB (@RequestBody AlbumModel albumModel){

        try {
            System.out.println(albumModel.toString());
            albumModelService.saveAlbumModelToDB(albumModel);
        } catch (UnableToSaveModelDataToDB e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to create the album", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Album saved to DB successfully", HttpStatus.OK);
    }

    @PutMapping("/updateAlbum")
    public ResponseEntity<String> updateAlbum( @RequestBody AlbumModel albumModel){
        try {
            albumModelService.updateAlbum(albumModel);
        } catch (ModelDataNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to update the album", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Album updated", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAlbum/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable("albumId") Long albumId){
        albumModelService.deleteAlbum(albumId);
        return new ResponseEntity<>("Album successfully removed", HttpStatus.OK);
    }

}
