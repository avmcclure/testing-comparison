package com.services;

import com.models.PhotoResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PlaceholderService {

    private final String placeholderUrl = "https://jsonplaceholder.typicode.com/photos?albumId=%s";
    private final RestTemplate restTemplate;

    public PlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PhotoResponse> getPhotosByAlbum(int albumId) {
        String albumUrl = String.format(placeholderUrl, albumId);
        var response = restTemplate.exchange(albumUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
        return response.getBody();
    }
}
