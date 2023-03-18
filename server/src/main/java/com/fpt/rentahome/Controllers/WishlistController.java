package com.fpt.rentahome.Controllers;


import com.fpt.rentahome.Dto.AddToWishlistRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse> addPropertyToWishlist(@RequestBody AddToWishlistRequest request) {
        wishlistService.addPropertyToWishlist(request.getPropertyId(), request.getUserId());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
    }


//    @GetMapping("/{clientId}")
//    public ResponseEntity<List<Property>> getWishList(@PathVariable int clientId) {
//
//        List<Wishlist> body = wishlistService.readWishList(clientId);
//        List<Property> properties = body.stream()
//                .map(Wishlist::getProperty)
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<List<Property>>(properties, HttpStatus.OK);
//    }
@GetMapping("/{clientId}")
public ResponseEntity<List<Wishlist>> getWishlistByClientId(@PathVariable("clientId") int clientId) {
    List<Wishlist> wishlistItems = wishlistService.readWishList(clientId);
    if(wishlistItems.isEmpty()) {
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.ok(wishlistItems);
    }
}






}

