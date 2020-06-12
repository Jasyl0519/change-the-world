package com.jason.web.test.spring.annotation;

import com.jason.web.test.spring.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@Component
public class TestEventHandler2  {

    @Async("event-async")
    @EventListener
    public void process(TestEvent2 testEvent) {

        System.out.println("publisher event2 start" +  " thread:" + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("publisher event2 end:" + testEvent.getMsg() +  " thread:" + Thread.currentThread().getName());

    }
}
