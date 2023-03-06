package com.fpt.rentahome;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Repositories.AdminRepository;
import com.fpt.rentahome.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RentahomeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RentahomeApplication.class, args);
    }

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
       Client client1 = Client.builder()
               .name("naima")
               .email("adardor")
              .phone("123")
              .password("123")
              .build();

       clientRepository.save(client1);
    }
}
