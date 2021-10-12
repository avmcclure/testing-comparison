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
        var url = String.format("http://localhost:%d/healthCheck", port);

        var actual = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Success", actual.getBody());
    }

    @Test
    public void getPhotoAlbum_ShouldReturnPhotos() {
        var albumId = 2;
        var url = String.format("http://localhost:%d/album/%d/photos", port, albumId);

        var actual = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});

        assertEquals(50, actual.getBody().size());
    }

    @Test(expected = HttpClientErrorException.class)
    public void getPhotoAlbum_ShouldReturnValidationErroWhenAlbumIdZero() {
        var albumId = 0;
        var url = String.format("http://localhost:%d/album/%d/photos", port, albumId);

        restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PhotoResponse>>() {});
    }
}
