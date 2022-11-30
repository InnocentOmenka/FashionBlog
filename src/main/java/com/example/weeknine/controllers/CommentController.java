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


    @PostMapping("/makeNewComment{pid}")
    public ResponseEntity<CommentDto> commentOnPost(@Valid @RequestParam long uid, @RequestBody CommentDto commentDto, @PathVariable long pid){
        return new ResponseEntity<>(commentService.newComment(uid, commentDto, pid), HttpStatus.CREATED);
    }

}
