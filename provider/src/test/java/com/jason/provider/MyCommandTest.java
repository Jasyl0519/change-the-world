package com.jason.provider;

import com.jason.provider.hystrix.threadStrategy.MyCommand;
import com.jason.provider.hystrix.threadStrategy.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author jasonz
 * @Description:
 * @date 2019-11-14
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCommandTest {
    @Test
    public void test() throws Exception {
        MyService service = new MyService();
        List<Future<String>> fl = new ArrayList<>(10);
        for (int i = 0; i < 20; i++) {
            Thread.sleep(200);
            MyCommand command = new MyCommand("TestGroup", "fishing" + i);
            command.setService(service);
            System.out.println("call times:"+(i+1)+" isCircuitBreakerOpen: "+command.isCircuitBreakerOpen());
            Observable<String> observable = command.toObservable();
            observable.subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("执行command发生错误！");
                    e.printStackTrace();
                }

                @Override
                public void onNext(String s) {
                    System.out.println(s);
                }
            });
        }

        Thread.sleep(30000);
    }
}