package com.example.auth.controller;

import com.example.auth.dto.PostResponse;
import com.example.auth.model.Post;
import com.example.auth.model.Tag;
import com.example.auth.model.User;
import com.example.auth.repository.PostRepository;
import com.example.auth.repository.TagRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.AuthService;
import com.example.auth.service.PostService;
import com.example.auth.service.TagService;
import lombok.AllArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private AuthService authService;
    private TagRepository tagRepository;
    private TagService tagService;


    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody Post post, Principal principal){
        String  currentUserName = principal.getName();
        User currentUser = userRepository.findByUsername(currentUserName).orElseThrow(null);
        post.setUser(currentUser);
//        Tag tag = tagRepository.findById(post.getTag().getId());
//        boolean isExisting= tagRepository.findById(post.getTag().getId()).isPresent();
//        if(isExisting){
//            post.setTag(post.getTag());
//        }
        Tag tag = tagService.getTagByTagName(post.getTag().getTagName());
        post.setTag(tag);
        post.setAuthor(currentUser.getUsername());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post){
        postService.updatePost(id,post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }


    @GetMapping("by-user")
    public ResponseEntity<List<Post>> getPostsByUsername(Principal principal){
        String  currentUserName = principal.getName().toString();
//        System.out.println(currentUserName);
        User currentUser = userRepository.findByUsername(currentUserName).orElseThrow(null);
        return status(HttpStatus.OK).body(postService.getPostsByUser(currentUser));
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("by-tag/{id}")
    public ResponseEntity<List<Post>> getPostsByTag(Tag tag){
        return status(HttpStatus.OK).body(postService.getPostsByTag(tag));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
