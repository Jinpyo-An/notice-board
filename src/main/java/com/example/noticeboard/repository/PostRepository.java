package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void createPost(Post newPost) {
        em.persist(newPost);
    }

    public Optional<Post> findWithAuthorById(Long postId) {
        String query = "SELECT p FROM Post p JOIN FETCH p.author WHERE p.id = :postId";
        return em.createQuery(query, Post.class)
                .setParameter("postId", postId)
                .getResultStream()
                .findFirst();
    }
}
