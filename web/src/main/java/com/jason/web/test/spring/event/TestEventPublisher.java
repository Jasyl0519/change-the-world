package com.jason.web.test.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@Component
public class TestEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void send() {
        TestEvent testEvent = new TestEvent(this, "test jason");
        applicationEventPublisher.publishEvent(testEvent);

    }
}
