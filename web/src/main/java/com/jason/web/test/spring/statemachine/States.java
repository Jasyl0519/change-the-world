package com.jason.web.test.spring.statemachine;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/12
 */
public enum States {

    UNPAID,                 // 待支付
    WAITING_FOR_RECEIVE,    // 待收货
    DONE                    // 结束
}
