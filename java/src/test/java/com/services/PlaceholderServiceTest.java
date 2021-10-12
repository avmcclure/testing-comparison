package com.services;

import com.models.PhotoResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PlaceholderServiceTest {
    private final int albumId = 2;
    private final String url = "https://jsonplaceholder.typicode.com/photos?albumId=%s";
    private PlaceholderService service;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        service = new PlaceholderService(restTemplate);
    }

    @Test
    public void getPhotosByAlbum_ShouldMakeCallToRestTemplate() {
        var photoResponses = List.of(new PhotoResponse());
        var response = new ResponseEntity<>(photoResponses, HttpStatus.OK);
        when(restTemplate.exchange(String.format(url, albumId), HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {})).thenReturn(response);
        service.getPhotosByAlbum(albumId);
        verify(restTemplate).exchange(String.format(url, albumId), HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
    }

    @Test
    public void getPhotosByAlbum_ShouldReturnResponseFromApiCall() {
        var response = new PhotoResponse();
        var collection = List.of(response);
        var entity = new ResponseEntity<>(collection, HttpStatus.OK);

        when(restTemplate.exchange(String.format(url, albumId), HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {})).thenReturn(entity);
        var actual = service.getPhotosByAlbum(albumId);

        assertEquals(collection, actual);
    }

}