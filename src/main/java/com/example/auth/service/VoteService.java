package com.example.auth.service;

import com.example.auth.dto.VoteDto;
import com.example.auth.exception.BlogException;
import com.example.auth.exception.PostNotFoundException;
import com.example.auth.model.Post;
import com.example.auth.model.Vote;
import com.example.auth.repository.PostRepository;
import com.example.auth.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.auth.model.VoteType.LIKE;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto){
        Post post = postRepository.findById(voteDto.getPostId()).orElseThrow(()->new PostNotFoundException("Post Not Found with ID - " +voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser=voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
            throw new BlogException("you have already " + voteDto.getVoteType() +"'d for this post");
        }
        if(LIKE.equals(voteDto.getVoteType())){
            post.setLikes(post.getLikes() + 1);
        }
        else{
            post.setLikes(post.getLikes() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
