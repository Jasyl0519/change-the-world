package com.jason.provider.hystrix.threadStrategy;

/**
 * @author jasonz
 * @Description:
 * @date 2019-11-14
 */
public class MyService {
    public void doSth(String thing) {
        System.out.println("doSth->" + thing + "->begin");
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ":doSth->" + thing + "->doing");
        try {
            Thread.sleep(15000);//这里让线程等待15s，保持线程不释放
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("doSth->" + thing + "->end");
    }
}