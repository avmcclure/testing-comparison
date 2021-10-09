package com.services;

import com.models.PhotoResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertArrayEquals;
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
        PhotoResponse[] photoResponses = {new PhotoResponse()};
        ResponseEntity<PhotoResponse[]> response = new ResponseEntity<>(photoResponses, HttpStatus.OK);
        when(restTemplate.getForEntity(String.format(url, albumId), PhotoResponse[].class)).thenReturn(response);
        service.getPhotosByAlbum(albumId);
        verify(restTemplate).getForEntity(String.format(url, albumId), PhotoResponse[].class);
    }

    @Test
    public void getPhotosByAlbum_ShouldReturnResponseFromApiCall() {
        var response = new PhotoResponse();
        PhotoResponse[] collection = new PhotoResponse[]{response};
        var entity = new ResponseEntity<>(collection, HttpStatus.OK);

        when(restTemplate.getForEntity(String.format(url, albumId), PhotoResponse[].class)).thenReturn(entity);
        var actual = service.getPhotosByAlbum(albumId);

        assertArrayEquals(collection, actual);
    }

}