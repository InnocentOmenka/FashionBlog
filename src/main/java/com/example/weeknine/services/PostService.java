package com.example.weeknine.services;

import com.example.weeknine.dtos.PostDto;
import com.example.weeknine.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    ResponseEntity<PostDto> makeNewPost(long uid, PostDto postDto);

    PostDto editPost(long uid, long pid, PostDto postDto);

    Post deletePost(long uid, long pid);

    List<Post> getUserPosts(long uid);
}
