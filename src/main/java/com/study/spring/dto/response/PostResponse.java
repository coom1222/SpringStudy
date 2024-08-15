package com.study.spring.dto.response;


import com.study.spring.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter


// @NoArgsConstructor 얘가 있으면 final오류

public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
