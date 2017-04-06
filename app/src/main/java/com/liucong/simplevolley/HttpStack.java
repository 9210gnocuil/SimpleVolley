package com.liucong.simplevolley;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by liucong on 2017/4/6.
 * 策略模式中的抽象策略
 * 有HttpUrlConnection和HttpClient两种方式访问网络
 * 抽象出一个访问网络的方法
 */

public interface HttpStack {
    NetworkResponse performRequest(Request<?> request) throws IOException;
}
