package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Wishlist;
import com.fpt.rentahome.Repositories.WishlistRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist addToWishList(Wishlist wishlist) {

        Client client = new Client();
        client.setId(wishlist.getClient().getId());

        Property property = new Property();
        property.setId(wishlist.getProperty().getId());

        wishlist.setClient(client);
        wishlist.setProperty(property);

        return wishlistRepository.save(wishlist);
    }
}
