package com.example.weeknine.services;

import com.example.weeknine.dtos.PostDto;
import com.example.weeknine.models.Post;
import com.example.weeknine.models.User;
import com.example.weeknine.repositories.PostRepository;
import com.example.weeknine.repositories.UserRepository;
import com.example.weeknine.services.serviceImpl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceImplTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PostServiceImpl underTest;

    @Autowired
    private ModelMapper mapper;
    private Post postUnderTest;
    private Post post;

    @MockBean
    private Post postMock;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new PostServiceImpl(postRepository, userRepository, mapper);

        user = User.builder()
                .id(1L)
                .username("mary2")
                .email("mary2@gmail.com")
                .role("buyer")
                .password("2222")
                .build();

        postUnderTest = Post.builder()
                .productName("garri")
                .price("three thousand")
                .content("All kinds of fruits")
                .category("food")
                .build();

        post = Post.builder()
                .postId(1L)
                .productName("garri")
                .price("three thousand")
                .user(user).content("All kinds of fruits")
                .category("food").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(postRepository.save(any())).thenReturn(post);
        when(userRepository.save(any())).thenReturn(user);
    }

    @Test
    void makePost(){
        long id = 1L;

        PostDto postDto = mapper.map(post, PostDto.class);
        ResponseEntity<PostDto> regDto = underTest.makeNewPost(id, postDto);
        assertEquals("garri", regDto.getBody().getProductName());
    }
}