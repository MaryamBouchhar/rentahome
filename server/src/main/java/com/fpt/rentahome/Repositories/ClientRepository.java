package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String username);

    //get only 6 newest clients
    @Query("SELECT c FROM Client c ORDER BY c.id DESC")
    List<Client> newestClients(Pageable pageble);
}
