package com.jason.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengcheng
 * @Description:
 * @date 2020/6/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response<T> {

    /** 状态 */
    private boolean success = true;
    /** 错误码 */
    private String code;
    /** 状态消息 */
    private String msg;
    /** 淘宝/tmall商品或店铺的返利信息;外网B2C商品或活动的返利信息 */
    private T data;
}
