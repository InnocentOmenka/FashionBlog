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

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/addNewPost")
    public ResponseEntity<PostDto> addNewPost(@Valid @RequestParam long uid, @RequestBody PostDto postDto){
        return postService.makeNewPost(uid, postDto);
    }


    @PutMapping("/editPost{pid}")
    public ResponseEntity<PostDto> editPost(@Valid @RequestParam long uid, @PathVariable long pid, @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.editPost(uid, pid, postDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete{pid}")
    public ResponseEntity<String> deletePost(@RequestParam long uid, @PathVariable long pid){
        postService.deletePost(uid, pid);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/userPosts")
    public ResponseEntity<List<Post>> getUserPosts(@Valid @RequestParam long uid){
        return ResponseEntity.ok(postService.getUserPosts(uid));
    }


}
