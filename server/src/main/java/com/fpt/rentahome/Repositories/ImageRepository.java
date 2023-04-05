package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Image;
import com.fpt.rentahome.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.sql.RowSet;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByUrl(String name);

    List<Image> findImageByProperty(Property property);

    @Transactional
    @Modifying
    @Query("DELETE FROM Image i WHERE i.property = :property")
    void deleteAllByProperty(@Param("property") Property property);
}
