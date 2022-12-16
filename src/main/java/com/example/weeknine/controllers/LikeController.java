package com.example.weeknine.controllers;

import com.example.weeknine.services.LikeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/likes")
@RestController
@AllArgsConstructor

public class LikeController {

    private final LikeService likeService;


    @GetMapping("like/{pid}/{id}")
    public ResponseEntity<String> likePost(@PathVariable long pid, @PathVariable long id){
        String msg = likeService.likePost(pid, id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
