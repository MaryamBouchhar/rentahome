package com.fpt.rentahome.Controllers;

import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Repositories.AdminRepository;
import com.fpt.rentahome.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AdminService adminService;



    @PostMapping("/auth")
    public ResponseEntity<ApiResponse> login(@RequestBody Admin admin) {
        Admin existingAdmin = adminService.findByEmail(admin.getEmail());

        if (existingAdmin == null || !existingAdmin.getPassword().equals(admin.getPassword())) {
            return new ResponseEntity<>(new ApiResponse(false, "failed login"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ApiResponse(true, "login"), HttpStatus.CREATED);
    }
}
