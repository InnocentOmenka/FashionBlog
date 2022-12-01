package com.example.weeknine.repositories;

import com.example.weeknine.models.Like;
import com.example.weeknine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByUser(User user);
}
