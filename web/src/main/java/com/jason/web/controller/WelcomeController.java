package com.jason.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author: lingyou
 * date: 2019-10-11 22:17
 */

@RestController
public class WelcomeController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello Spring Boot";
    }


}
