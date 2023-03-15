package com.fpt.rentahome.Controllers;


import com.fpt.rentahome.Helpers.ApiResponse;
import com.fpt.rentahome.Models.Wishlist;
import com.fpt.rentahome.Services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addItemToWishlist(@RequestBody Wishlist wishlistItem){

        wishlistService.addItemToWishlist(wishlistItem);

        return new ResponseEntity<>(new ApiResponse(true, "ITEM ADDED TO WISHLIST"), HttpStatus.CREATED);
    }

}

