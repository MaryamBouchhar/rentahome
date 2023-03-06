package com.fpt.rentahome;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentahomeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RentahomeApplication.class, args);
    }

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {
       /*Admin admin1 = Admin.builder()
                .name("naima")
                .email("adardor")
               .password("123")
                .build();

        Admin admin2 = Admin.builder()
                .name("saad")
                .email("libourki")
                .password("1134")
                .build();
        adminRepository.save(admin1);
        adminRepository.save(admin2);*/
    }
}
