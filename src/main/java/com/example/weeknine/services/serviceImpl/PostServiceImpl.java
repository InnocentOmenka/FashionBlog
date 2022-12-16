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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Component
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;


    @Transactional
    @Override
    public ResponseEntity<PostDto> makeNewPost(long id, PostDto postDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Post post = mapToEntity(postDto);
            post.setUser(user.get());
            Post newPost = postRepository.save(post);
            PostDto postResponse = mapToDto(newPost);
            return new ResponseEntity<>(postResponse, HttpStatus.OK);
        } else {
            throw new InvalidRequestException("user not found");
        }
    }

    @Override
    public PostDto editPost(long pid, PostDto postDto) {
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("Post not found"));

        if (postDto.getProductName() != null && !postDto.getProductName().equals(post.getProductName())) {
            post.setProductName(postDto.getProductName());
            post.setUpdatedDate(LocalDateTime.now());
        }

        if (postDto.getPrice() != null && !postDto.getPrice().equals(post.getPrice())) {
            post.setPrice(postDto.getPrice());
            post.setUpdatedDate(LocalDateTime.now());
        }

        if (postDto.getContent() != null && !postDto.getContent().equals(post.getContent())) {
            post.setContent(postDto.getContent());
            post.setUpdatedDate(LocalDateTime.now());
        }

        if (postDto.getCategory() != null && !postDto.getCategory().equals(post.getCategory())) {
            post.setCategory(postDto.getCategory());
            post.setUpdatedDate(LocalDateTime.now());
        }

        postRepository.save(post);
        return postDto;
    }


    @Override
    public Post deletePost( long pid) {
        Post post = postRepository.findById(pid).
                orElseThrow(() -> new InvalidUserException("Invalid post"));
        if(post.getUser().getId() == pid){
            postRepository.delete(post);
            return post;
        }
        else{
            throw new InvalidRequestException("Invalid user");
        }
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
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
