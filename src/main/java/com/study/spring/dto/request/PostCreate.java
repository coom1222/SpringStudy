package com.study.spring.dto.request;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter



public class PostCreate {


    // 게시물을 생성할 때 제목은 필수이므로
    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String content;




    @Builder
    public PostCreate(String content, String title) {
        this.content = content;
        this.title = title;
    }
}
