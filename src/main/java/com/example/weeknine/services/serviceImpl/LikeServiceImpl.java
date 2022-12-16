package com.example.weeknine.services.serviceImpl;

import com.example.weeknine.exceptions.InvalidUserException;
import com.example.weeknine.models.Like;
import com.example.weeknine.models.Post;
import com.example.weeknine.models.User;
import com.example.weeknine.repositories.CommentRepository;
import com.example.weeknine.repositories.LikeRepository;
import com.example.weeknine.repositories.PostRepository;
import com.example.weeknine.repositories.UserRepository;
import com.example.weeknine.services.LikeService;
import com.example.weeknine.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;



    public int getAllPostLikes(long pid) {
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("User not found"));
        return post.getLikes().size();
    }

    @Override
    public String likePost(long pid, long id) {

        Post post = postRepository.findById(pid).
                orElseThrow(() -> {
                    throw new InvalidUserException("User not found");
                });

        User user = userRepository.findById(id).
                orElseThrow(() -> {
                    throw new InvalidUserException("Not found");
                });

        Optional<Like> liked = likeRepository.findByPostAndUser(post, user);
        if (liked.isPresent()) {
            likeRepository.delete(liked.get());
            return "User unliked post";
        }

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);

        likeRepository.save(like);
        return "User liked Post";
    }
}
