package com.fpt.rentahome.Controllers;


import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Admin;
import com.fpt.rentahome.Repositories.AdminRepository;
import com.fpt.rentahome.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/manage-admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //ADD AN ADMIN
    @PostMapping("/add-admin")
    public ResponseEntity<ApiResponse> createAdmin(@RequestBody Admin admin) {
//        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
//        }
        adminService.createAdmin(admin);
        return new ResponseEntity<>(new ApiResponse(true, "created the admin"), HttpStatus.CREATED);
    }

    //FETCH ALL ADMINS
    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    //UPDATE AN ADMIN
    @PutMapping("/update-admin/{id}")
    public ResponseEntity<ApiResponse> updateAdmin(@PathVariable("id") int id, @RequestBody Admin admin) {

        ApiResponse response = new ApiResponse();
        HttpStatus status;

        // Call the service method to update the admin with the given ID
        boolean success = adminService.updateAdmin(id, admin);

        if (success) {
            response.setMessage("Admin updated successfully");
            status = HttpStatus.OK;
        } else {
            response.setMessage("Admin not found");
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, status);
    }
}


