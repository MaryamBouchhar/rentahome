package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Property;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    //@Query("SELECT DISTINCT e.category FROM property e")
    //List<String> findAllValues();
}
