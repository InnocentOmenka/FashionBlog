package com.example.weeknine.services;


import com.example.weeknine.dtos.CommentDto;
import com.example.weeknine.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listOfPostComments(long pid);
    CommentDto newComment(long uid, CommentDto commentDto, long pid);

}
