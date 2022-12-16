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
    public CommentDto newComment(long pid, CommentDto commentDto) {
        String content = commentDto.getCommentContent();

        long id = commentDto.getId();

        Post post = postRepository.findById(pid)
                .orElseThrow(() -> {
                    throw new InvalidUserException ("Not Found");
                });
        User user = userRepository.findById(id)
                .orElseThrow(() -> new InvalidUserException( "Not Found"));
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
        return commentDto;
    }

    @Override
    public String deleteComment(long cid) {
        Comment comment = commentRepository.findById(cid).
                orElseThrow(()-> new InvalidUserException("Comment with ID: "+ cid + "is not found"));
        commentRepository.delete(comment);

        return "Comment Deleted Successfully";

    }



    @Override
    public List<Comment> getAllPostComment(long pid) {
        Post post = postRepository.findById(pid)
                .orElseThrow(() -> new InvalidUserException("Post with ID: " + pid + " is not Found"));

        return commentRepository.findAllCommentByPost(post);
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
