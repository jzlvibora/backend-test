package com.example.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Nullable
    private String image;
    @Nullable
    @Lob
    private String body;
    private Integer likes;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName="id")
    private User user;
//    private Instant createdAt;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="tagId", referencedColumnName = "id")
//    private Tag tag;
}
