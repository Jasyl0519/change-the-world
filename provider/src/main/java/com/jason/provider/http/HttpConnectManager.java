package com.jason.provider.http;

/**
 * Description:
 * Author: lingyou
 * date: 2019-11-14 22:27
 */

import com.google.common.collect.Lists;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程-使用连接池管理HTTP请求
 * @author: liangpengju
 * @version: 1.0
 */
public class HttpConnectManager {
    private static final ExecutorService service = Executors.newFixedThreadPool(40);

    public static void main(String[] args) throws Exception {
        //创建HTTP的连接池管理对象
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //将最大连接数增加到200
        connectionManager.setMaxTotal(100);
        //将每个路由的默认最大连接数增加到20
        connectionManager.setDefaultMaxPerRoute(5);


        //发起3次GET请求
        String url1 ="http://localhost:8080/hello";
        String url2 ="http://localhost:9091/hello";
        //long start = System.currentTimeMillis();
        //doGet(connectionManager, url1);

        //将http://www.baidu.com:80的最大连接增加到50

        HttpHost httpHost1 = new HttpHost("localhost", 8080);
        HttpHost httpHost2 = new HttpHost("localhost", 9091);

        connectionManager.setMaxPerRoute(new HttpRoute(httpHost1),10);
        connectionManager.setMaxPerRoute(new HttpRoute(httpHost2),20);



        List<Thread> threadList = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doGet(connectionManager, url1);
                    } catch (Exception e) {
                        System.out.println("thread1:"+ e.fillInStackTrace());
                    }
                }
            });

            threadList.add(thread1);
        }


        for (int i = 0; i < 20; i++) {
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        doGet(connectionManager, url2);
                    } catch (Exception e) {
                        System.out.println("thread2:"+ e.fillInStackTrace());
                    }
                }
            });
            threadList.add(thread2);

        }

        for (Thread thread : threadList) {
            service.execute(thread);


        }




        System.out.println("MaxPerRoute1:" + connectionManager.getMaxPerRoute(new HttpRoute(httpHost1)));
        System.out.println("MaxPerRoute2:" + connectionManager.getMaxPerRoute(new HttpRoute(httpHost2)));

        Thread.sleep(1000 * 1000);



        //long end = System.currentTimeMillis();
        //System.out.println("consume -> " + (end - start));

        //清理无效连接
        //new IdleConnectionEvictor(connectionManager).start();
    }

    /**
     * 请求重试处理
     * @param tryTimes 重试次数
     * @return
     */
    public static HttpRequestRetryHandler retryHandler(final int tryTimes){

        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                // 如果已经重试了n次，就放弃
                if (executionCount >= tryTimes) {
                    return false;
                }
                // 如果服务器丢掉了连接，那么就重试
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                // 不要重试SSL握手异常
                if (exception instanceof SSLHandshakeException) {
                    return false;
                }
                // 超时
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                // 目标服务器不可达
                if (exception instanceof UnknownHostException) {
                    return true;
                }
                // 连接被拒绝
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                // SSL握手异常
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext .adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        return httpRequestRetryHandler;
    }

    /**
     * doGet
     * @param url 请求地址
     * @param connectionManager
     * @throws Exception
     */
    public static void doGet(HttpClientConnectionManager connectionManager, String url) throws Exception {
        //从连接池中获取client对象，多例
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager).build();
                //.setRetryHandler(retryHandler(5)).build();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        // 构建请求配置信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间
                .setConnectionRequestTimeout(2000) // 从连接池中获取到连接的最长时间
                .setSocketTimeout(30 * 1000) // 数据传输的最长时间10s
                .setStaleConnectionCheckEnabled(true) // 提交请求前测试连接是否可用
                .build();
        // 设置请求配置信息
        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容：" + content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            // 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
            // httpClient.close();
        }
    }

    /**
     * 监听连接池中空闲连接，清理无效连接
     */
    public static class IdleConnectionEvictor extends Thread {

        private final HttpClientConnectionManager connectionManager;

        private volatile boolean shutdown;

        public IdleConnectionEvictor(HttpClientConnectionManager connectionManager) {
            this.connectionManager = connectionManager;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        //3s检查一次
                        wait(3000);
                        // 关闭失效的连接
                        connectionManager.closeExpiredConnections();
                    }
                }
            } catch (InterruptedException ex) {
                // 结束
                ex.printStackTrace();
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }
}
