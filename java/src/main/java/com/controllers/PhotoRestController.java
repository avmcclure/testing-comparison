package com.controllers;

import com.models.PhotoResponse;
import com.services.PlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhotoRestController {

    private final PlaceholderService placeholderService;

    @Autowired
    public PhotoRestController(PlaceholderService placeholderService) {
        this.placeholderService = placeholderService;
    }

    @GetMapping("/healthCheck")
    public String getHealth() {
        return "Success";
    }

    @GetMapping("/album/{albumId}/photos")
    public List<PhotoResponse> getPhotoAlbum(@PathVariable int albumId) {
        return placeholderService.getPhotosByAlbum(albumId);
    }
}
