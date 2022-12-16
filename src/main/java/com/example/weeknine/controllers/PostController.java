package com.example.weeknine.controllers;

import com.example.weeknine.dtos.PostDto;
import com.example.weeknine.models.Post;
import com.example.weeknine.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {


    private final PostService postService;


    @PostMapping("/addNewPost/{id}")
    public ResponseEntity<PostDto> addNewPost(@Valid @PathVariable long id, @RequestBody PostDto postDto){
        return postService.makeNewPost(id, postDto);
    }

    @GetMapping("/all-post")
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/editPost/{pid}")
    public ResponseEntity<PostDto> editPost(@Valid  @PathVariable long pid, @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.editPost(pid, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<String> deletePost( @PathVariable long pid){
        postService.deletePost(pid);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
