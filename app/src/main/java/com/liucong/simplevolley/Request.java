package com.liucong.simplevolley;

/**
 * Created by liucong on 2017/4/6.
 * 请求对象
 */

public abstract class Request<T> {

    private String mUrl;
    private RequestQueue mRequestQueue;
    private Response.ErrorListener mErrorListener;

    public Request(String mUrl, Response.SuccessListener<String> mListener, Response.ErrorListener mErrorListener) {

        this.mUrl = mUrl;
        this.mErrorListener = mErrorListener;
    }

    private boolean mResponseDelivered = false;

    public boolean isResponseDelivered() {
        return mResponseDelivered;
    }

    public void setResponseDelivered(boolean mResponseDelivered) {
        this.mResponseDelivered = mResponseDelivered;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /**
     * 返回与Reuqest关联的请求队列
     * @return
     */
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * 将Reuqest与请求队列关联起来
     * @param mRequestQueue
     */
    public void setRequestQueue(RequestQueue mRequestQueue) {
        this.mRequestQueue = mRequestQueue;
    }

    /**
     * 子类重写这个方法来解析网络数据
     * 这个方法是将原始的网络数据根据不同类型的请求解析成不同泛型的Response
     * @param response
     * @return
     */
    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

    /**
     * 这个方法是用来将解析过的Response传递给它们的自己的listener
     * 这个Listener在创建Request的时候创建出来的，
     * 这个Listener一般需要具体Request来持有，
     *
     * 网络请求是父类完成的，具体子类将原始数据解析成想要的类型，
     * 然后具体子类将将解析好了的数据分发出去
     *
     * @param response
     */
    abstract protected void deliverResponse(T response);

    protected void deliverError(Throwable error){}
}
