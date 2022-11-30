package com.example.weeknine.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_Id", nullable = false)
    private Long postId;

    private String productName;
    private  double price;
    private  String content;
    private  String category;
    private LocalDateTime datePosted;

    @JsonIgnore
    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();



}
