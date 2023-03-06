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

@RequestMapping("/manage-client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    //FETCH ALL Clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

}
