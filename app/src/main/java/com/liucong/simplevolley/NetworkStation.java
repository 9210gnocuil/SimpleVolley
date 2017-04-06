package com.liucong.simplevolley;

import java.io.IOException;

/**
 * Created by liucong on 2017/4/6.
 * 策略模式中的环境角色
 *
 */

public class NetworkStation {

    //策略模式中的环境持有抽象策略的引用
    private HttpStack httpStack;

    public NetworkStation(HttpStack httpStack) {
        this.httpStack = httpStack;
    }

    public NetworkResponse performRequest(Request<?> request) throws IOException {
        return httpStack.performRequest(request);
    }
}
