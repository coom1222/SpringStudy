package com.study.spring.controller;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring.domain.Post;
import com.study.spring.dto.request.PostCreate;
import com.study.spring.repository.PostRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.match.ContentRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javax.management.Query.value;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/post 잘 되는지")
    void test1() throws Exception {
    //given
        PostCreate post = PostCreate.builder()
                .title("")
                .content("")
                .build();

        String json = objectMapper.writeValueAsString(post);

        //expected

        ResultActions resultActions = mockMvc.perform(post("/post")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("글 단건조회 요청")
    void test2() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        postRepository.save(post);
        //expected


        mockMvc.perform(get("/post/{postId}", post.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(post.getId()))
                .andDo(print());



    }



    @Test
    @DisplayName("게시글이 없을 시 에러를 반환하는지")
    void test4() throws Exception {

        //expected

        mockMvc.perform(get("/post/{postId}", 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());



    }

    @Test
    @DisplayName("게시물 전체조회 요청")
    void test5() throws Exception {

        //expected
        List<Post> postList = IntStream.range(0, 10)
                .mapToObj(i ->{
                    return Post.builder()
                            .title("제목입니다" + i)
                            .content("내용입니다" + i)
                            .build();
                })
                .collect(Collectors.toList());

        postRepository.saveAll(postList);

        mockMvc.perform(get("/post")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(10)))
                .andExpect(jsonPath("$[9].title", value("제목입니다9")))
                .andExpect(jsonPath("$[9].content", value("제목입니다9")))
                .andDo(print());


    }

}
