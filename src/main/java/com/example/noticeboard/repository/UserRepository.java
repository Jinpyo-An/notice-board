package com.example.noticeboard.repository;

import com.example.noticeboard.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }
}
