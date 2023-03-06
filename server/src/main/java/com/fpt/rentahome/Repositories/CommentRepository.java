package com.fpt.rentahome.Repositories;

import com.fpt.rentahome.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPropertyId(Long propertyId);
}
