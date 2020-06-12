package com.jason.web.test.spring.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
public class TestEvent extends ApplicationEvent {

    @Getter
    private String msg;

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

}
