package com.example.auth.repository;

import com.example.auth.model.Comment;
import com.example.auth.model.Post;
import com.example.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);

}
