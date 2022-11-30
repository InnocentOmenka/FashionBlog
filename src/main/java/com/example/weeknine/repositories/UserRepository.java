package com.example.weeknine.repositories;
import com.example.weeknine.models.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailOrUsername(String email, String username);
    Optional<User> findByEmail(String email);
}
