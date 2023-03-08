package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Repositories.AdminRepository;
import com.fpt.rentahome.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public List<Client> getAllClients() {
        return clientRepository.findAll();

    }
    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }
}
