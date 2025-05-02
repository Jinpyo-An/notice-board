package com.example.noticeboard.service;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.dto.post.PostDetailResponse;
import com.example.noticeboard.exception.PostNotFoundException;
import com.example.noticeboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    /**
     * 트랜잭션 전파 고민
     */

    private final PostRepository postRepository;

    public PostDetailResponse getPostDetail(Long postId) {
        final Post post = postRepository.findWithAuthorById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getAuthor().getId(),
                post.getAuthor().getUsername()
        );
    }
}
