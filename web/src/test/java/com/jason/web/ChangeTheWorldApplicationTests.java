package com.jason.web;

import com.jason.web.test.ClassA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeTheWorldApplicationTests {


    @Autowired
    private ClassA classA;

    @Test
    public void test1() {

        System.out.println(11111);
    }

}
