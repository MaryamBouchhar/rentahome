package com.fpt.rentahome;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentahomeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RentahomeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
