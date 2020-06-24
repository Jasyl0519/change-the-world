package com.jason.web.statemachine;

import com.jason.web.test.spring.statemachine.Events;
import com.jason.web.test.spring.statemachine.States;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StateMachineTest {

    @Autowired
    private StateMachine<States, Events> stateMachine;


    @Test
    public void test() {

        stateMachine.start();
        //stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);

    }
}
