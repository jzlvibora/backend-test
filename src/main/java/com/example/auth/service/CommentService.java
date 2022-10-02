package com.example.auth.service;

import com.example.auth.exception.PostNotFoundException;
import com.example.auth.model.Comment;
import com.example.auth.model.Post;
import com.example.auth.model.User;
import com.example.auth.repository.CommentRepository;
import com.example.auth.repository.PostRepository;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentService {
    private static final String POST_URL="";


    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AuthService authService;


    public void createComment(Comment comment) {
        Post post = postRepository.findById(comment.getPostIdentity()).orElseThrow(()->new PostNotFoundException(comment.getPostIdentity().toString()));
       comment.setPost(post);
       comment.setUser(authService.getCurrentUser());
       comment.setCreatedAt(Instant.now());
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByUser(String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user);

    }

    public List<Comment> getCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post);
    }
}
