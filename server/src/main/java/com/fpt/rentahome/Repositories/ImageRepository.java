package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByUrl(String name);
}
