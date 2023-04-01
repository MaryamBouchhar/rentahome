package com.fpt.rentahome.Controllers;


import com.fpt.rentahome.Dto.AddToWishlistRequest;
import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Wishlist;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Services.ClientService;
import com.fpt.rentahome.Services.WishlistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addPropertyToWishlist(@RequestBody AddToWishlistRequest request) {
        wishlistService.addPropertyToWishlist(request.getPropertyId(), request.getUserId());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
    }


    @GetMapping("/{client_id}")
    public ResponseEntity<List<Property>> getWishList(@PathVariable("client_id") Integer clientId) {
        Optional<Client> c = clientService.getClientById(clientId);
        //convert optional to Object
        Client client = c.orElseThrow(() -> new EntityNotFoundException("Client not found"));
        List<Wishlist> body = wishlistService.readWishList(client);
        List<Property> properties = new ArrayList<Property>();
        for (Wishlist wishList : body) {
            properties.add(wishList.getProperty());
        }

        return new ResponseEntity<List<Property>>(properties, HttpStatus.OK);
    }


}

