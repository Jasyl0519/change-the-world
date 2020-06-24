package com.jason.web.test.spring.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@Slf4j
@WithStateMachine
public class EventConfig {


    @OnTransition(target = "UNPAID")
    public void create() {

        log.info("订单创建,待支付");

    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {

        log.info("用户完成支付,待收货");

    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {

        log.info("用户已收货,订单完成");

    }




}
