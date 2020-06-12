package com.jason.web.test.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@Component
public class TestEventHandler implements ApplicationListener<TestEvent> {

    @Async("event-async")
    @Override
    public void onApplicationEvent(TestEvent testEvent) {

        System.out.println("publisher event start" +  " thread:" + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("publisher event end:" + testEvent.getMsg() + " thread:" + Thread.currentThread().getName());

    }
}
