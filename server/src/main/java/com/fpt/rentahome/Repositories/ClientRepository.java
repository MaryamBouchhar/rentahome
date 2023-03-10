package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String username);
}
