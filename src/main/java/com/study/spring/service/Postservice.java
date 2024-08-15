package com.study.spring.service;


import com.study.spring.domain.Post;
import com.study.spring.dto.request.PostCreate;
import com.study.spring.dto.response.PostResponse;
import com.study.spring.exception.post.PostNotFound;
import com.study.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class Postservice {

    private final PostRepository postRepository;

    public void write(PostCreate request) {
        // 여기가 서비스 로직

        Post post = Post.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .build();

        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        // optional 이 붙은 이유는 post가 null일수도 있다.
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 글 입니다. "));


        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .build();
    }

    public List<PostResponse> getAll() {
        List<Post> postList = postRepository.findAll();

        return postList.stream().map(PostResponse::new).collect(Collectors.toList());
    }
}
