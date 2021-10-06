package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoRestController {

    @GetMapping("/healthCheck")
    public String getHealth() {
        return "Success";
    }
}
