package com.fpt.rentahome.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ClientController {
    @GetMapping("/")
    public String hello() {
        return "Test 1";
    }

    @GetMapping("/hello")
    public String hello2() {
        return "Hello from GET";
    }
}
