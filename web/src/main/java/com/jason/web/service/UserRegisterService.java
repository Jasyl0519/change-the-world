package com.jason.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020-03-26
 */
@Service
public class UserRegisterService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void register() {
        publisher.publishEvent("你好");
    }


}
