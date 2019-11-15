package com.jason.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: lingyou
 * date: 2019-10-15 22:53
 */
@Component
public class ClassA {

    private String name;

    @Autowired
    private ClassB classB;


    public void test() {
        classB.test();

    }

    public void test1() {
        System.out.println("ClassA test1");


    }
}
