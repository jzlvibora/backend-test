package com.example.auth.service;

import com.example.auth.exception.TagNotFoundException;
import com.example.auth.model.Tag;
import com.example.auth.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public Tag getTag(Long id){
        Tag tag = tagRepository.findById(id).orElseThrow(()->new TagNotFoundException(id.toString()));
        return tag;
    }

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }
}
