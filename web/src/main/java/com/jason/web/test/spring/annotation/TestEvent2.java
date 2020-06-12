package com.jason.web.test.spring.annotation;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
public class TestEvent2 {

    @Getter
    private String msg;

    public TestEvent2(String msg) {
        this.msg = msg;
    }

}
