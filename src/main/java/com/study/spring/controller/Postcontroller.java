package com.study.spring.controller;


import com.study.spring.dto.request.PostCreate;
import com.study.spring.dto.response.PostResponse;
import com.study.spring.service.Postservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class Postcontroller {
    // put : 리소스를 완전히 대체한다. (부분수정 불가), patch:부분수정 가능
    // delete : 삭제하는 메서드

    private final Postservice postService; //앞은 타입 뒤는 변수

    @PostMapping("/post")
    public void post(@RequestBody PostCreate request) {

        postService.write(request);
    }

    @GetMapping("/post/{postId}")   // 클라이언트한테 정보를 넘겨줄거임. 그러기 위해선 넘겨줄 객체가 필요, dto 객체를 만들어줄 예정
    public PostResponse get(@PathVariable("postId") Long id){
        PostResponse postResponse = postService.get(id);
        return postResponse;
    }

    @GetMapping("/post")
    public List<PostResponse> getAll(){
        return postService.getAll();
    }

}
