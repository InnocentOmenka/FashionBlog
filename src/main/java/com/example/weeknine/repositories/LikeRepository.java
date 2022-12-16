package com.example.weeknine.repositories;

import com.example.weeknine.models.Like;
import com.example.weeknine.models.Post;
import com.example.weeknine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByPostAndUser(Post post, User user);

    Like findByUser(User user);
}
