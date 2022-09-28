package com.example.auth.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tagName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
//    @OneToMany(fetch=FetchType.LAZY,  mappedBy="tag")
//    private List<Post> posts = new ArrayList<Post>();
}
