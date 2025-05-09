package com.example.noticeboard.service;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.domain.User;
import com.example.noticeboard.exception.PostNotFoundException;
import com.example.noticeboard.exception.UserNotFoundException;
import com.example.noticeboard.repository.PostRepository;
import com.example.noticeboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post getPostDetail(Long postId) {
        return postRepository.findWithAuthorById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    @Transactional
    public Long createPost(Long authorId, String title, String content) {
        User author = userRepository.findUserById(authorId)
                .orElseThrow(() -> new UserNotFoundException(authorId));

        Post newPost = new Post(title, content, author);
        postRepository.createPost(newPost);

        return newPost.getId();
    }
}
