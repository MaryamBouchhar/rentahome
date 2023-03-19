package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;

import com.fpt.rentahome.Services.AdminService;
import com.fpt.rentahome.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //FETCH ALL Clients
    @CrossOrigin(origins = "http://localhost:5174")
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    //Update Client BY ID
    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }
}
