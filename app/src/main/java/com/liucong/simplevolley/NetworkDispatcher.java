package com.liucong.simplevolley;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liucong on 2017/4/6.
 * 用于分发网络请求
 */

public class NetworkDispatcher extends Thread {

    private LinkedBlockingQueue<Request> mQueue;
    private NetworkStation mStation;
    private ResponseDelivery mDelivery;

    //构造方法
    public NetworkDispatcher(String threadName,LinkedBlockingQueue<Request> mQueue,
                             NetworkStation mStation,ResponseDelivery mDelivery) {
        super(threadName);
        this.mQueue = mQueue;
        this.mStation = mStation;
        this.mDelivery = mDelivery;
    }

    @Override
    public void run() {
        super.run();

        while(true){

            try {
                //1.从队列中取出Request
                Request request = mQueue.take();

                //2.开始请求网络
                NetworkResponse networkResponse = mStation.performRequest(request);

                //3.然后调用Request中的解析网络响应的原始数据
                Response response = request.parseNetworkResponse(networkResponse);

                //4.将解析后的响应传递过去
                mDelivery.postResponse(request,response);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
