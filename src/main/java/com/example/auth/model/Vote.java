package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter

public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long voteId;
    private VoteType voteType;
    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postId", referencedColumnName = "id")
    private Post post;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName = "id")
    private User user;
}
