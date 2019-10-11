package com.jason.web.controller;

import com.jason.web.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author: lingyou
 * date: 2019-10-11 22:17
 */

@RestController
public class WelcomeController {

    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/hello")
    public String index() {
        return "Hello Spring Boot1 " + authorSettings.getName() + "_" + authorSettings.getAge();
    }


}
