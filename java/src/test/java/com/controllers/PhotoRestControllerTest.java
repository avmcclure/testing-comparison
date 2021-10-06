package com.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhotoRestControllerTest {

    private PhotoRestController restController;

    @Before
    public void setUp() {
        restController = new PhotoRestController();
    }

    @Test
    public void getHealth_ShouldReturnSuccess() {
        String actual = restController.getHealth();

        assertEquals("Success", actual);
    }

}