package com.services;

import com.models.PhotoResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlaceholderServiceTest {
    private final int albumId = 2;
    private final String url = "https://jsonplaceholder.typicode.com/photos?albumId=%s";
    private PlaceholderService service;
    private RestTemplate mockRest;

    @Before
    public void setup() {
        mockRest = mock(RestTemplate.class);
        service = new PlaceholderService(mockRest);
    }

    @Test
    public void getPhotosByAlbum_ShouldMakeCallToRestTemplate() {
        service.getPhotosByAlbum(albumId);
        verify(mockRest).getForEntity(String.format(url, albumId), PhotoResponse[].class);
    }
}