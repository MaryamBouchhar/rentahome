package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
