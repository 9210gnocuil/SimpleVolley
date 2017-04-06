package com.liucong.simplevolley;

/**
 * Created by liucong on 2017/4/6.
 * 响应
 */

public class NetworkResponse {
    private byte[] responseContent;
    private int statusCode;
    //private Map<String,String> header;


    public NetworkResponse(byte[] responseContent, int statusCode) {
        this.responseContent = responseContent;
        this.statusCode = statusCode;
    }

    public byte[] getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(byte[] responseContent) {
        this.responseContent = responseContent;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
