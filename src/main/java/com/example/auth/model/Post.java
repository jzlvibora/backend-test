package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String image;
    @Nullable
    @Lob
    private String body;
    private Integer likes;
    private boolean isLiked;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName="id")
    private User user;

    //may doubt sa pagalalagay ng cascade dito. ibig sabihin pag binura ko yung post mabubura din lahat ng tag ?
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tagId", referencedColumnName = "id")
    private Tag tag;

//    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
//    private Set<Comment> comments;
}
