package com.jason.web.test.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@Component
public class TestEventPublisher2  {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    public void send() {
        TestEvent2 testEvent = new TestEvent2("test jason");
        applicationEventPublisher.publishEvent(testEvent);

    }
}
