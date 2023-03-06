package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
