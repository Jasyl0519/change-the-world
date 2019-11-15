package com.jason.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: lingyou
 * date: 2019-10-15 22:53
 */
@Component
public class ClassB {

    private String name;

    @Autowired
    private ClassA classA;

    public void test() {
        System.out.println("ClassB Test");
        classA.test1();

    }


}
