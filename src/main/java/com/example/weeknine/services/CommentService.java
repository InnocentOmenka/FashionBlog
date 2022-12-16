package com.example.weeknine.services;


import com.example.weeknine.dtos.CommentDto;
import com.example.weeknine.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listOfPostComments(long pid);
    CommentDto newComment(long pid, CommentDto commentDto);
    //Comment editComment(long cid, CommentDto commentDto);

    String deleteComment(long cid);

    List<Comment> getAllPostComment(long pid);
}
