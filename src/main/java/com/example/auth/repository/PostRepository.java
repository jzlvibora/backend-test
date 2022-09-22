package com.example.auth.repository;

import com.example.auth.model.Post;
import com.example.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(Optional<User> user);
}
