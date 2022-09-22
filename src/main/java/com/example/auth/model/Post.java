package com.example.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName="id")
    private User user;
//    private Instant createdAt;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="tagId", referencedColumnName = "id")
//    private Tag tag;
}
