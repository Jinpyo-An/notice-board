package com.example.noticeboard.controller;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.dto.IdResponse;
import com.example.noticeboard.dto.PostDetailResponse;
import com.example.noticeboard.dto.PostRequest;
import com.example.noticeboard.service.PostService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/{postId}")
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable @Min(1) Long postId) {
        Post post = postService.getPostDetail(postId);

        PostDetailResponse response = PostDetailResponse.from(post);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping
    public ResponseEntity<IdResponse> createPost(@RequestBody @Valid PostRequest request) {
        Long authorId = request.getAuthorId();
        String title = request.getTitle();
        String content = request.getContent();

        Long newPostId = postService.createPost(authorId, title, content);
        IdResponse response = IdResponse.from(newPostId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
