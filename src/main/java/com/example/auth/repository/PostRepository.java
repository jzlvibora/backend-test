package com.example.auth.repository;

import com.example.auth.model.Post;
import com.example.auth.model.Tag;
import com.example.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> , PagingAndSortingRepository<Post,Long> {
    List<Post> findAll();
    List<Post> findByUser(User user);
    List<Post> findByTag(Tag tag);
}
