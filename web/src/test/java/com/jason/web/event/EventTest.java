package com.jason.web.event;

import com.jason.web.test.spring.annotation.TestEventPublisher2;
import com.jason.web.test.spring.event.TestEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {


    @Autowired
    private TestEventPublisher testEventPublisher;

    @Test
    public void testEventPublisher() {
        testEventPublisher.send();



        System.out.println("hello" +  " thread:" + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Autowired
    private TestEventPublisher2 testEventPublisher2;

    @Test
    public void testEventPublisher2() {
        testEventPublisher2.send();



        System.out.println("hello" +  " thread:" + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
