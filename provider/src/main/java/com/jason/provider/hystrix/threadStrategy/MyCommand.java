package com.jason.provider.hystrix.threadStrategy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * @author jasonz
 * @Description:
 * @date 2019-11-14
 */
public class MyCommand extends HystrixCommand<String> {

    private final String group;
    private MyService service;
    private String thing;

    public MyCommand(String group, String thing) {
//    super(HystrixCommandGroupKey.Factory.asKey(group));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(group))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("myCommand"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("myCommandThreadPool"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerRequestVolumeThreshold(5)
                                        .withCircuitBreakerErrorThresholdPercentage(60)
                                        .withExecutionTimeoutInMilliseconds(2000)
                                        //.withExecutionTimeoutEnabled(false)//配合下方MyService的等待15s，防止超时直接报错
                                        .withMetricsRollingStatisticalWindowInMilliseconds(10000))
                        .andThreadPoolPropertiesDefaults(
                                HystrixThreadPoolProperties.Setter()
                                        .withCoreSize(10))//这里我们设置了线程池大小为10
        );
        this.group = group;
        this.thing = thing;
    }

    @Override
    protected String run() throws Exception {
        service.doSth(thing);
        return thing + " over";
    }
    @Override
    protected String getFallback() {
        return thing + " Failure! ";
    }

    public void setService(MyService service) {
        this.service = service;
    }
}
