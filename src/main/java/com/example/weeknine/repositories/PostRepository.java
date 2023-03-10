package com.example.weeknine.repositories;

import com.example.weeknine.models.Post;
import com.example.weeknine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllPostsByUser(User user);
}
