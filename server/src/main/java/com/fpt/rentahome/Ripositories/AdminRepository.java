package com.fpt.rentahome.Ripositories;

import com.fpt.rentahome.Models.Admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
