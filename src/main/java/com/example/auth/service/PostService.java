package com.example.auth.service;

import com.example.auth.exception.PostNotFoundException;
import com.example.auth.model.Post;
import com.example.auth.model.Tag;
import com.example.auth.model.User;
import com.example.auth.repository.PostRepository;
import com.example.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
private final PostRepository postRepository;
private final UserRepository userRepository;
private AuthService authService;
private TagService tagService;

    public Post getPost(Long id) {
       Post post = postRepository.findById(id).orElseThrow(()->new PostNotFoundException(id.toString()));
       return post;
    }

    public Post save( Post post) {
        return postRepository.save(post);
    }


    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }


    public List<Post> getPostsByUsername() {
//        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        Authentication user = (Authentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postRepository.findByUser((User) user);
    }


    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> getPostsByTag(Tag tag) {
        return postRepository.findByTag(tag);
    }

    public void deletePost(Long id) {
      postRepository.deleteById(id);
    }

    public void updatePost(Long id, Post post) {
        Post postToUpdate=postRepository.findById(id).get();
        postToUpdate.setTitle(post.getTitle());
        Tag newTag= tagService.getTagByTagName(post.getTag().getTagName());
        postToUpdate.setTag(newTag);
        postToUpdate.setBody(post.getBody());
        postToUpdate.setImage(post.getImage());
        postToUpdate.setLikes(post.getLikes());
       postRepository.save(postToUpdate);

    }
}
