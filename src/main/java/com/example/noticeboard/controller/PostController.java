package com.example.noticeboard.controller;

import com.example.noticeboard.dto.post.PostDetailResponse;
import com.example.noticeboard.service.PostService;
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
        PostDetailResponse response = postService.getPostDetail(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
