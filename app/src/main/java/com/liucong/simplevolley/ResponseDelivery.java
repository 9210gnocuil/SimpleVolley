package com.liucong.simplevolley;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.UiThread;

/**
 * Created by liucong on 2017/4/6.
 * 将网络请求结果传递给各自的Listener
 */

public class ResponseDelivery {

    private static final int POST_RESPONSE = 1;
    private static final int POST_ERROR = 2;

    private Response response;
    private Throwable error;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Request request = (Request) msg.obj;
            switch (msg.what){
                case POST_RESPONSE:
                    request.deliverResponse(response.response);
                    break;
                case POST_ERROR:
                    request.deliverError(error);
                    break;
            }
        }
    };

    public ResponseDelivery(){

    }

    public void postResponse(Request<?> request,Response<?> response){
        this.response = response;
        Message message = handler.obtainMessage();
        message.obj = request;
        message.what = POST_RESPONSE;
        handler.sendMessage(message);
    }

    public void postError(Request<?> request,Throwable error){
        this.error = error;
        Message message = handler.obtainMessage();
        message.what = POST_ERROR;
        message.obj = request;
        handler.sendMessage(message);
    }


}
