package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Localisation, Integer> {
}
