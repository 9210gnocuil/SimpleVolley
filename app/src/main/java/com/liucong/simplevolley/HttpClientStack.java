package com.liucong.simplevolley;

/**
 * Created by liucong on 2017/4/6.
 * 策略模式中的具体策略
 */

public class HttpClientStack implements HttpStack {
    @Override
    public NetworkResponse performRequest(Request<?> request) {
        return null;
    }
}
