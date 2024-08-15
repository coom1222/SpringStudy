package com.study.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

}
