package com.example.weeknine.services.serviceImpl;

import com.example.weeknine.dtos.PostDto;
import com.example.weeknine.exceptions.InvalidRequestException;
import com.example.weeknine.exceptions.InvalidUserException;
import com.example.weeknine.models.Post;
import com.example.weeknine.models.User;
import com.example.weeknine.repositories.PostRepository;
import com.example.weeknine.repositories.UserRepository;
import com.example.weeknine.services.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Component
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public ResponseEntity<PostDto> makeNewPost(long uid, PostDto postDto) {
        Optional<User> user = userRepository.findById(uid);
        if (user.isPresent()) {
            Post post = mapToEntity(postDto);
            post.setDatePosted(LocalDateTime.now());
            post.setUser(user.get());
            Post newPost = postRepository.save(post);
            PostDto postResponse = mapToDto(newPost);
            return new ResponseEntity<>(postResponse, HttpStatus.OK);
        } else {
            throw new InvalidRequestException("user not found");
        }
    }

    @Override
    public PostDto editPost(long uid, long pid, PostDto postDto) {
        Post post = postRepository.getById(pid);
        if (post.getUser().getId() == uid) {
            post = mapToEntity(postDto);
            Post updatePost = postRepository.save(post);
            PostDto updatedEdit = mapToDto(updatePost);
            return updatedEdit;
        }
        else {
            throw new InvalidRequestException("Invalid user");
        }
    }
    @Override
    public Post deletePost(long uid, long pid) {
        Post post = postRepository.findById(pid).
                orElseThrow(() -> new InvalidUserException("Invalid post"));
        if(post.getUser().getId() == uid){
            postRepository.delete(post);
            return post;
        }
        else{
            throw new InvalidRequestException("Invalid user");
        }
    }

    @Override
    public List<Post> getUserPosts(long uid) {
        User user = userRepository.findById(uid).orElseThrow(() ->new InvalidUserException("User not found"));
        return user.getListOfPosts();
    }

    public PostDto mapToDto(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }


    //convert DTO into entity
    public Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
        return post;
    }
}
