package com.jason.web.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020-03-26
 */
@Component
public class UserRegisterEventListener {

    @EventListener
    public void handlerEvent(String s) {

        System.out.println(s);

    }
}
