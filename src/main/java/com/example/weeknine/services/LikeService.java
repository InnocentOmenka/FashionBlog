package com.example.weeknine.services;

public interface LikeService {
    void likePost(long pid, long uid);

    String unlikePost(long uid, long pid);
}
