package com.example.weeknine.repositories;

import com.example.weeknine.models.Comment;
import com.example.weeknine.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllCommentByPost(Post post);
}
