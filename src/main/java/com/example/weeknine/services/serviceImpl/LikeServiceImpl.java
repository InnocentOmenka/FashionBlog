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

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public void likePost(long pid, long uid) {
        Like like = new Like();
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        like.setUser(user);
        likeRepository.save(like);
        Post post = postRepository.findById(pid).orElseThrow(()-> new InvalidUserException("Invalid Id"));
        post.getLikes().add(like);
        postRepository.save(post);
    }

    @Override
    public String unlikePost(long uid, long pid) {
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        Like like = likeRepository.findByUser(user);
        Post post = postRepository.findById(pid).orElseThrow(()-> new InvalidUserException("Invalid user"));
        List<Like> postLikes = post.getLikes();
        for (int i = 0; i < postLikes.size(); i++){
            if (postLikes.get(i) == like){
                postLikes.remove(i);
            }
        }
        postRepository.save(post);
        return "Unlike post successfully";
    }


    public int getAllPostLikes(long pid) {
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("User not found"));
        return post.getLikes().size();
    }

}
