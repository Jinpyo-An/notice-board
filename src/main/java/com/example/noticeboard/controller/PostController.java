package com.example.noticeboard.controller;

import com.example.noticeboard.dto.PostDetailResponse;
import com.example.noticeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 메서드 명은 HTTP 동사와 매핑되게 짓는 것이 일반적
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/{postId}", produces = "application/json;charset=UTF-8")
    public PostDetailResponse getPostDetail(@PathVariable Long postId) {
        /**
         * todo
         * 매개변수로 조회할 게시물 id를 받는다.
         * PostService에 게시물 상세 조회 메서드를 호출한다. 매개변수로 postId를 넘겨준다.
         * 호출한 결과 값을 받아 사용자에게 응답한다.
         * 잘못된 요청일시, 예외 처리를 한다.
         **/

        return postService.getPostDetail(postId);
    }
}
