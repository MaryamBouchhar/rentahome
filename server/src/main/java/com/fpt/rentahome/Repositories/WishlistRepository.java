package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Client;
import com.fpt.rentahome.Models.Property;
import com.fpt.rentahome.Models.Wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

    // find all the properties saved to wishlist for a user
    // sort by latest created date
    List<Wishlist> findAllByClientOrderByCreatedDateDesc(Integer clientId);

}
