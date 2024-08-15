package com.study.spring.exception.post;

import com.study.spring.exception.GlobalException;

/*
* 상태코드 : 404
*
* */

public class PostNotFound extends GlobalException {

    public static final String MESSAGE = "Post not found";

    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
