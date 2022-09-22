package com.example.auth.controller;

import com.example.auth.model.Post;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.PostService;
import lombok.AllArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody Post post, Principal principal){
        String  currentUserName = principal.getName();
       User currentUser = userRepository.findByUsername(currentUserName);
        post.setUser(currentUser);
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }


    @GetMapping("by-user")
    public ResponseEntity<List<Post>> getPostsByUsername(Principal principal){
        String  currentUserName = principal.getName();
//        System.out.println(currentUserName);
        User currentUser = userRepository.findByUsername(currentUserName);
        return status(HttpStatus.OK).body(postService.getPostsByUser(currentUser));
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

//    @GetMapping("by-tag/{id}")
//    public ResponseEntity<List<PostResponse>> getPostsByTag(Long id){
//        return status(HttpStatus.OK).body(postService.getPostsByTag(id));
//    }


}
