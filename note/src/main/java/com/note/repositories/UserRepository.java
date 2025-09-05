package com.note.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.note.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> existsByEmail(String email);

    Optional<User> deleteByEmail(String email);

    Optional<User> findByEmail(String email);


}
