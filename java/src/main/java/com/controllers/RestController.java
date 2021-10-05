package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class RestController {

    @GetMapping("/healthCheck")
    public String getHealth() {
        return "Success";
    }
}
