package com.example.weeknine.services.serviceImpl;

import com.example.weeknine.dtos.CommentDto;
import com.example.weeknine.dtos.RegistrationDto;
import com.example.weeknine.exceptions.InvalidUserException;
import com.example.weeknine.models.Comment;
import com.example.weeknine.models.Post;
import com.example.weeknine.models.User;
import com.example.weeknine.repositories.CommentRepository;
import com.example.weeknine.repositories.PostRepository;
import com.example.weeknine.repositories.UserRepository;
import com.example.weeknine.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Data
@Component
@AllArgsConstructor

public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private ModelMapper mapper;
    @Override
    public List<Comment> listOfPostComments(long pid) {
        return null;
    }

    @Override
    public CommentDto newComment(long uid, CommentDto commentDto, long pid) {
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("User not found"));

        Comment comment = mapToEntity(commentDto);
        comment.setTimePosted(LocalDateTime.now());
        comment.setUser(user);

        Comment newComment = commentRepository.save(comment);
        post.getComments().add(newComment);
        postRepository.save(post);
        CommentDto postComment = mapToDto(newComment);
        return postComment;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }


    public Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }

}
