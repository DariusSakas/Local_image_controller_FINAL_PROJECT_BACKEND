package com.example.local_image_controller_final_project_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageFileToDownloadNotFound extends Exception {

    public ImageFileToDownloadNotFound(String message) {
        super(message);
    }

    public ImageFileToDownloadNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
