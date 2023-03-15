package com.fpt.rentahome.Services;

import com.fpt.rentahome.Models.Comment;
import com.fpt.rentahome.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
