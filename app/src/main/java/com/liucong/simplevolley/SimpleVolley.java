package com.liucong.simplevolley;

import android.os.Handler;

/**
 * Created by liucong on 2017/4/6.
 * 精简版的Volley
 */

public class SimpleVolley {

    public static RequestQueue newRequestQueue(){

        ResponseDelivery delivery = new ResponseDelivery();
        HttpStack httpStack = new HttpURLConnectionStack();
        NetworkStation networkStation = new NetworkStation(httpStack);

        RequestQueue queue = new RequestQueue(networkStation,delivery);

        queue.start();
        return queue;
    }

}
