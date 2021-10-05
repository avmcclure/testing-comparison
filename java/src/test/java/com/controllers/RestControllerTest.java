package com.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestControllerTest {

    private RestController restController;

    @Before
    public void setUp() {
        restController = new RestController();
    }

    @Test
    public void getHealth_ShouldReturnSuccess() {
        String actual = restController.getHealth();

        assertEquals("Success", actual);
    }

}