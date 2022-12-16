package com.example.weeknine.services;

import com.example.weeknine.dtos.PostDto;
import com.example.weeknine.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<PostDto> makeNewPost(long id, PostDto postDto);

    PostDto editPost( long pid, PostDto postDto);

    Post deletePost(long id);

    List<Post> getAllPosts();
}
