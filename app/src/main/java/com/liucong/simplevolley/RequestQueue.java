package com.liucong.simplevolley;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liucong on 2017/4/6.
 * 请求队列
 */

public class RequestQueue {

    private NetworkDispatcher[] mDispatchers;
    private static final int DEFAULT_THREAD_POOL_SIZE = 4;

    private NetworkStation mStation;
    private ResponseDelivery mDelivery;

    private static final LinkedBlockingQueue<Request> mNetworkQueue = new LinkedBlockingQueue<>();

    public RequestQueue(NetworkStation mStation, ResponseDelivery mDelivery) {
        this.mStation = mStation;
        this.mDelivery = mDelivery;
        this.mDispatchers = new NetworkDispatcher[DEFAULT_THREAD_POOL_SIZE];
    }

    //启动网络线程
    public void start(){
        //在启动前先停止所有的网络线程
        //stop();

        //默认创建4个网络线程 并启动
        for(int i = 0;i<mDispatchers.length;i++){
            NetworkDispatcher dispatcher =
                    new NetworkDispatcher("Dispatcher #"+i,mNetworkQueue,mStation,mDelivery);
            mDispatchers[i] = dispatcher;
            dispatcher.start();
        }
    }

    /**
     * 将Request添加到请求队列中
     * @param request
     */
    public Request add(Request request){
        // 首先将Request与这个请求队列关联起来
        // 以便随时更改Request状态
        request.setRequestQueue(this);

        //添加到队列
        mNetworkQueue.add(request);
        return request;
    }

    public void stop(){
        if(mDispatchers!=null){
            for(NetworkDispatcher dispatcher:mDispatchers){
                if(dispatcher.isAlive()){
                    dispatcher.interrupt();
                }
            }
        }
    }

}
