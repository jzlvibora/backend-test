package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String text;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="postId", referencedColumnName = "id")
    private Post post;



    private Long postIdentity;
    private Instant createdAt;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName = "id")
    private User user;

}
