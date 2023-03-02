package com.fpt.rentahome.controllers;

import com.fpt.rentahome.models.Client;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/post")
    public String helloPost(@RequestBody Client client) {
        //string json to object
        System.out.printf("Name: %s", client.getName());
        System.out.printf("Email: %s", client.getEmail());
        System.out.printf("Phone: %s", client.getPhone());
        System.out.printf("Password: %s", client.getPassword());
    }
