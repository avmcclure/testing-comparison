package com.services;

import com.models.PhotoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PlaceholderService {

    private final String placeholderUrl = "https://jsonplaceholder.typicode.com/photos?albumId=%s";
    private RestTemplate restTemplate;

    public PlaceholderService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public PhotoResponse[] getPhotosByAlbum(int albumId) {
        String albumUrl = String.format(placeholderUrl, albumId);
        var response = restTemplate.getForEntity(albumUrl, PhotoResponse[].class);
        return response.getBody();
    }
}
