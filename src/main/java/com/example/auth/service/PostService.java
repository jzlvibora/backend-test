package com.example.auth.service;

import com.example.auth.model.Post;
import com.example.auth.model.User;
import com.example.auth.repository.PostRepository;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
private final PostRepository postRepository;
private final UserRepository userRepository;


    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }
}
