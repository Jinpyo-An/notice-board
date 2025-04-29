package com.example.noticeboard.service;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.dto.PostDetailResponse;
import com.example.noticeboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 메서드 명: 비즈니스 로직을 수행하는 메서드, 행위를 나타내는 메서드명으로
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDetailResponse getPostDetail(Long postId) {
        final Post post = postRepository.findWithAuthorById(postId);

        // todo post가 null일 때 exception 처리 해야 함

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
