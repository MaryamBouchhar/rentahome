package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Property;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    List<Property> findByPriceRange(@Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice);

    List<Property> findByCategory(String category);

    @Query("SELECT p FROM Property p WHERE p.location.city = :location")
    List<Property> findByCity(String location);

    @Query("SELECT p FROM Property p WHERE p.description LIKE %:search% OR p.category LIKE %:search% OR p.location.city LIKE %:search%")
    List<Property> filterBySearch(String search);
}
