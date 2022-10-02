package com.example.auth.controller;

import com.example.auth.model.Comment;
import com.example.auth.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody Comment comment){
        commentService.createComment(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllCommentsByUser(@RequestParam("userName") String userName){
        return status(HttpStatus.OK).body(commentService.getCommentsByUser(userName));
    }
}
