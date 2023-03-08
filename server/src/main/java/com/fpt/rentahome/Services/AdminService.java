package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


@Service
public class AdminService  {

    @Autowired
    private AdminRepository adminRepository;



//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByEmail(email);
//        if (admin == null) {
//            throw new UsernameNotFoundException("Admin not found");
//        }
//        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(),
//                new ArrayList<>());
//    }

    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }



    public boolean updateAdmin(int id, Admin updatedAdmin) {
        // Check if an admin with the given ID exists
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (!adminOptional.isPresent()) {
            return false;
        }

        // Update the admin object
        Admin admin = adminOptional.get();
        admin.setName(updatedAdmin.getName());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setPassword(updatedAdmin.getPassword());


        // Save the updated admin to the database
        adminRepository.save(admin);

        return true;

    }


    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);

    }
}
