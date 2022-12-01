package com.example.weeknine.controllers;

import com.example.weeknine.services.LikeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/likes")
@RestController
@AllArgsConstructor

public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like/{pid}")
    public void likePost(@PathVariable long pid, @RequestParam long uid) {
        likeService.likePost(pid, uid);
    }

    @PostMapping("/unlike/{pid}")
    public String unlikePost(@PathVariable long pid, @RequestParam long uid){
        return likeService.unlikePost(uid, pid);
    }
}
