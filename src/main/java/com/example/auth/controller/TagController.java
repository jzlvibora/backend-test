package com.example.auth.controller;

import com.example.auth.model.Tag;
import com.example.auth.repository.TagRepository;
import com.example.auth.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {
    @Autowired
    private  TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @PostMapping
    public ResponseEntity<Void> createTag(@RequestBody Tag tag){
        tagService.save(tag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(){
        return status(HttpStatus.OK).body(tagService.getAllTags());
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id){return status(HttpStatus.OK).body(tagService.getTag(id));}
}
