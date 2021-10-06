package com.integration;

import com.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestIntegration {

    @LocalServerPort
    private int port;

    @Test
    public void getHealth_ShouldReturnSuccessMessage() {
        var url = String.format("http://localhost:%d/healthCheck", port);
        var restTemplate = new RestTemplate();

        var actual = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Success", actual.getBody());
    }
}
