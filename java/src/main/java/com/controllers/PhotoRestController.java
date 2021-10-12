package com.controllers;

import com.models.PhotoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoRestController {

    @GetMapping("/healthCheck")
    public String getHealth() {
        return "Success";
    }

    @GetMapping("/album/{albumId}/photos")
    public PhotoResponse getPhotoAlbum(@PathVariable String albumId) {
        System.out.println("Album Id: " + albumId);
        return new PhotoResponse();
    }
}
