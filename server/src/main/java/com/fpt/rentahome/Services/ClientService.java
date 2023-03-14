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

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public boolean existsByEmail(String email) {
        return clientRepository.findByEmail(email) != null;
    }

    public Optional<Client> getClientByEmail(String email) {
        return Optional.ofNullable(clientRepository.findByEmail(email));
    }

    public Client updateClient(int id, Client client) {
        Client client1 = clientRepository.findById(id).get();
        client1.setName(client.getName());
        client1.setEmail(client.getEmail());
        client1.setPhone(client.getPhone());
        client1.setPassword(client.getPassword());
        clientRepository.save(client1);
        return client1;
    }
}
