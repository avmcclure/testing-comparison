package com.services;

import com.models.PhotoResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlaceholderService {

    private final String placeholderUrl = "https://jsonplaceholder.typicode.com/photos?albumId=%s";
    private final RestTemplate restTemplate;

    public PlaceholderService() {
        this(new RestTemplate());
    }

    public PlaceholderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PhotoResponse> getPhotosByAlbum(int albumId) {
        if (albumId == 0 || albumId > 100)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid albumId");
        String albumUrl = String.format(placeholderUrl, albumId);
        var response = restTemplate.exchange(albumUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
        return response.getBody();
    }
}
