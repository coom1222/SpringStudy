package com.study.spring.dto.response;

/*
*
* "code" : "400",
* "message" : "잘못된 요청입니다"
* "validation" : {
*       "title" : "제목을 입력하세요"
* }
*
*
* */

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter

public class ErrorResponse {

    private final String message;
    private final String code;

    private final Map<String, String> validation;


    @Builder
    public ErrorResponse(String message, String code, Map<String, String> validation) {
        this.message = message;
        this.code = code;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    // 받아온 필드값과 메시지
    // field값은 아까 만들었던 title, message는 NotBlankd 에 넣어놨던 message값
    public void addValidation(String field, String message) {
        this.validation.put(field, message);
    }





}
