package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Post findWithAuthorById(Long postId) {
        String query = "SELECT p FROM Post p JOIN FETCH p.author WHERE p.id = :postId";
        return em.createQuery(query, Post.class)
                .setParameter("postId", postId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
