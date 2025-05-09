package com.example.noticeboard.service;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.domain.User;
import com.example.noticeboard.exception.PostNotFoundException;
import com.example.noticeboard.repository.PostRepository;
import com.example.noticeboard.repository.UserRepository;
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

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void 게시물_상세조회_성공() {
        //given
        User author = new User(1L, "jinpyo", LocalDateTime.now());
        Post post = new Post(1L, "Title", "Content", LocalDateTime.now(), LocalDateTime.now(), author);

        when(postRepository.findWithAuthorById(1L)).thenReturn(Optional.of(post));

        //when
        Post findPost = postService.getPostDetail(1L);

        //then
        assertThat(findPost.getId()).isEqualTo(1L);
        assertThat(findPost.getTitle()).isEqualTo("Title");
        assertThat(findPost.getContent()).isEqualTo("Content");
        assertThat(findPost.getAuthor().getId()).isEqualTo(1L);
        assertThat(findPost.getAuthor().getUsername()).isEqualTo("jinpyo");
    }

    @Test
    void 게시물_상세조회_실패_게시물이없는경우() {
        // given
        when(postRepository.findWithAuthorById(99L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> postService.getPostDetail(99L))
                .isInstanceOf(PostNotFoundException.class);
    }

    @Test
    void 게시물_작성_성공() {

    }
}