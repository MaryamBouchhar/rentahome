package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Page<Admin> findAll(Pageable pageable);
    Admin findByEmail(String email);
}
