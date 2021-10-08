package com.services;

import com.models.PhotoResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
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
        PhotoResponse photoResponse = new PhotoResponse();
        photoResponse.setAlbumId(this.albumId);
        photoResponse.setTitle("Maybe Someday");
        photoResponse.setId(37);
        photoResponse.setThumbnailUrl("pretend this is your thumbnail url");
        photoResponse.setUrl("pretend this is your album url");

        ResponseEntity<PhotoResponse[]> fakeResponse = createFakeResponseWith(photoResponse);
        when(restTemplate.getForEntity(String.format(url, albumId), PhotoResponse[].class)).thenReturn(fakeResponse);
        PhotoResponse[] photosByAlbum = service.getPhotosByAlbum(albumId);

        assertSame(photosByAlbum[0], photoResponse);
    }

    @Test
    public void getPhotosByAlbum_ShouldReturnResponseFromApiCall() {
        var response = new PhotoResponse();
        PhotoResponse[] collection = new PhotoResponse[] {response};
        var entity = new ResponseEntity<>(collection, HttpStatus.OK);

        when(restTemplate.getForEntity(String.format(url, albumId), PhotoResponse[].class)).thenReturn(entity);
        var actual = service.getPhotosByAlbum(albumId);

        assertArrayEquals(collection, actual);
    }

    public ResponseEntity<PhotoResponse[]> createFakeResponseWith(PhotoResponse... photoResponses) {
        String albumUrl = String.format(url, albumId);
        ResponseEntity<PhotoResponse[]> fakeResponse = new ResponseEntity<>(HttpStatus.OK) {
            @Override
            public PhotoResponse[] getBody() {
                return photoResponses;
            }
        };
        return fakeResponse;

    }


}