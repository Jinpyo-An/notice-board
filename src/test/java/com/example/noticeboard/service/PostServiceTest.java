package com.example.noticeboard.service;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.domain.User;
import com.example.noticeboard.dto.post.PostDetailResponse;
import com.example.noticeboard.exception.PostNotFoundException;
import com.example.noticeboard.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void 게시물_상세조회_성공() {
        //given
        User author = new User(1L, "jinpyo", LocalDateTime.now());
        Post post = new Post(1L, "Title", "Content", LocalDateTime.now(), LocalDateTime.now(), author);

        when(postRepository.findWithAuthorById(1L)).thenReturn(Optional.of(post));

        //when
        PostDetailResponse response = postService.getPostDetail(1L);

        //than
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Title");
        assertThat(response.getContent()).isEqualTo("Content");
        assertThat(response.getAuthorId()).isEqualTo(1L);
        assertThat(response.getAuthorName()).isEqualTo("jinpyo");
    }

    @Test
    void 게시물_상세조회_실패_게시물이없는경우() {
        // given
        when(postRepository.findWithAuthorById(99L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> postService.getPostDetail(99L))
                .isInstanceOf(PostNotFoundException.class);
    }
}