package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Ripositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void createAdmin(Admin admin) {

        adminRepository.save(admin);
    }
}
