package com.study.spring.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter // 자바에서 배운 게터. 필드를 가져오는것
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 디비마다 전략이 달라 생성할 때 오류가 날 수 있다.
    private Long id;//아이디

    private String title;//제목

    @Lob
    private String content;//내용

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
