package com.integration;

import com.Application;
import com.models.PhotoResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestIntegrationIT {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void getHealth_ShouldReturnSuccessMessage() {
        //arrange
        var url = String.format("http://localhost:%d/healthCheck", port);
        //act
        var actual = restTemplate.getForEntity(url, String.class);
        //assert
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Success", actual.getBody());
    }

    @Test
    public void getPhotoAlbum_ShouldReturnPhotos() {
        //arrange
        var albumId = 2;
        var url = String.format("http://localhost:%d/album/%d/photos", port, albumId);
        //act
        var actual = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
        //assert
        assertEquals(50, actual.getBody().size());
    }

    @Test(expected = HttpClientErrorException.class)
    public void getPhotoAlbum_ShouldReturnValidationErroWhenAlbumIdZero() {
        //arrange
        var albumId = 0;
        var url = String.format("http://localhost:%d/album/%d/photos", port, albumId);
        //act
        restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
    }

    @Test(expected = HttpClientErrorException.class)
    public void getPhotoAlbum_ShouldReturnValidationErroWhenAlbumIdOverOneHundred() {
        //arrange
        var albumId = 101;
        var url = String.format("http://localhost:%d/album/%d/photos", port, albumId);
        //act
        restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
    }
}
