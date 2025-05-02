package com.example.noticeboard.controller;

import com.example.noticeboard.domain.Post;
import com.example.noticeboard.domain.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        User author = new User("jinpyo");
        em.persist(author);

        Post post = new Post("Spring Boot Introduction", "This is a detailed post about Spring Boot", author);
        em.persist(post);

        em.flush();
        em.clear();
    }

    @Test
    void 게시물_상세조회_성공() throws Exception {
        Long postId = 1L;

        mockMvc.perform(get("/api/posts/{postId}", postId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Boot Introduction"))
                .andExpect(jsonPath("$.authorName").value("jinpyo"))
                .andExpect(jsonPath("$.content").value("This is a detailed post about Spring Boot"));
    }

    @Test
    void 게시물_상세조회_존재하지않는게시물() throws Exception {
        Long postId = 1L;

        mockMvc.perform(get("/api/posts/{postId}", postId).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("해당 게시물을 찾을 수 없습니다. (id: " + postId + ")"))
                .andExpect(jsonPath("$.status").value(404));
    }
}