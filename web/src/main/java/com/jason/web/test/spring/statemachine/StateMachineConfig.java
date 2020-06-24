package com.jason.web.test.spring.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {

        states.withStates().initial(States.UNPAID).states(EnumSet.allOf(States.class));

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {

        transitions
                .withExternal()
                    .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                    .event(Events.PAY)
                    .and()
                .withExternal()
                    .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                    .event(Events.RECEIVE);

    }



//    @Bean
//    public StateMachineListener<States, Events> listener() {
//
//        return new StateMachineListenerAdapter<States, Events>() {
//
//            @Override
//            public void transition(Transition<States, Events> transition) {
//                if (transition.getTarget().getId() == States.UNPAID) {
//                    log.info("订单创建,待支付");
//                    return;
//                }
//
//                if (transition.getSource().getId() == States.UNPAID
//                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//                    log.info("用户完成支付,待收货");
//                    return;
//                }
//
//                if (transition.getSource().getId() == States.WAITING_FOR_RECEIVE
//                        && transition.getTarget().getId() == States.DONE) {
//                    log.info("用户已收货,订单完成");
//                    return;
//                }
//
//
//            }
//
//
//        };
//    }
//
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//
//        config.withConfiguration().listener(listener());
//    }



}
