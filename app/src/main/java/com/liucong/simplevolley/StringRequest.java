package com.liucong.simplevolley;

/**
 * Created by liucong on 2017/4/6.
 * 每一个具体类型的请求都需要重写
 * parseNetworkResponse
 * 和deliverResponse
 *
 *
 */

public class StringRequest extends Request<String> {

    private Response.SuccessListener<String> mListener;

    /**
     * 构造方法一定要注意,在赋值之前需要将必要的数据传给父类 调用父类的构造
     * 因为请求网络并返回原始数据这些过程是由父类来完成的。
     * @param mUrl
     * @param mListener
     * @param mErrorListener
     */
    public StringRequest(String mUrl, Response.SuccessListener<String> mListener, Response.ErrorListener mErrorListener) {

        super(mUrl,mListener,mErrorListener);
        this.mListener = mListener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        byte[] responseContent = response.getResponseContent();
        String newString = new String(responseContent);
        return Response.success(newString);
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }
}
