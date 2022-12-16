package com.example.weeknine.controllers;

import com.example.weeknine.dtos.CommentDto;
import com.example.weeknine.models.Comment;
import com.example.weeknine.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all/{pid}")
    public ResponseEntity<List<Comment>> getAllComments(@Valid @PathVariable Long pid){
        return ResponseEntity.ok(commentService.listOfPostComments(pid));
    }


    @PostMapping("/makeNewComment/{pid}")
    public ResponseEntity<CommentDto> commentOnPost(@Valid @PathVariable long pid, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.newComment(pid, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/comments/{pid}")
    public ResponseEntity<List<Comment>> getAllPostComment(@PathVariable long pid){
        List<Comment> comments = commentService.getAllPostComment(pid);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cid}")
    public ResponseEntity<String> deleteComment(@PathVariable long cid){
        commentService.deleteComment(cid);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.ACCEPTED);
    }


}
