package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClientController {
    ClientRepository clientRepository;
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
    @GetMapping("/clients")
    public List<Client> getAllClient() {
        return (List<Client>) clientRepository.findAll();
    }
}
