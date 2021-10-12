package com.controllers;

import com.services.PlaceholderService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PhotoRestControllerTest {

    private PhotoRestController restController;
    private PlaceholderService placeholderService;

    @Before
    public void setUp() {
        placeholderService = mock(PlaceholderService.class);
        restController = new PhotoRestController(placeholderService);
    }

    @Test
    public void getHealth_ShouldReturnSuccess() {
        var actual = restController.getHealth();

        assertEquals("Success", actual);
    }

    @Test
    public void getPhotoAlbum_ShouldCallPlaceholderService() {
        var albumId = 1;
        restController.getPhotoAlbum(albumId);

        verify(placeholderService).getPhotosByAlbum(albumId);
    }

}