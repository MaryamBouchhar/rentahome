package com.fpt.rentahome.Controllers;


import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Wishlist;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.WishlistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @PostMapping("/add/{propertyId}")
    public Wishlist addPropertyToWishlist(@PathVariable Integer propertyId, Authentication authentication) {
        Client client = (Client) authentication.getPrincipal();

        return wishlistService.addPropertyToWishlist(propertyId,client);

    }



}

