package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Wishlist;
import com.fpt.rentahome.Repositories.ClientRepository;
import com.fpt.rentahome.Repositories.PropertyRepository;
import com.fpt.rentahome.Repositories.WishlistRepository;
import com.mysql.cj.jdbc.ClientInfoProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Setter
@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Wishlist addPropertyToWishlist(Integer propertyId,Integer clientId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Property not found"));
        Wishlist wishlist = Wishlist.builder()
                .client(client)
                .property(property)
                .createdDate(new Date())
                .build();
        return wishlistRepository.save(wishlist);

    }

    public List<Wishlist> readWishList(int clientId) {
        return wishlistRepository.findByClientId(clientId);
    }

//    public List<Wishlist> getAllWishlistItems() {
//        return wishlistRepository.findAll();
//    }
}
