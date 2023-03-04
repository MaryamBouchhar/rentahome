package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Models.Client;
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

        return "Hello from POST, " + client.getName();
    }

    @DeleteMapping("/delete/{id}")
    public String helloDelete(@PathVariable int id) {
        System.out.println(id);
        return "Delete " + id;
    }
}