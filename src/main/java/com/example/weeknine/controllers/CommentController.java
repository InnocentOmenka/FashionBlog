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


    @PostMapping("/makeNewComment{cid}")
    public ResponseEntity<CommentDto> commentOnPost(@Valid @RequestParam long uid, @RequestBody CommentDto commentDto, @PathVariable long cid){
        return new ResponseEntity<>(commentService.newComment(uid, commentDto, cid), HttpStatus.CREATED);
    }

    @PutMapping("/editComment/{cid}")
    public ResponseEntity<Comment> editComment(@Valid @RequestParam long uid, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.editComment(uid, uid, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{cid}")
    public ResponseEntity<String> deleteComment(@RequestParam long uid, @PathVariable long cid){
        commentService.deleteComment(cid, uid);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
