package com.liucong.simplevolley;

/**
 * Created by liucong on 2017/4/6.
 */

public class Response<T> {

    public T response;
    public Throwable error;

    public Response(T response) {
        this.response = response;
        this.error = null;
    }

    public Response(Throwable error) {
        this.response = null;
        this.error = error;
    }

    public interface SuccessListener<T>{
        void onResponse(T response);
    }

    public interface ErrorListener{
        void onError(Throwable error);
    }

    public static <T> Response success(T result){
        return new Response<T>(result);
    }

    public static <T> Response error(Throwable error){
        return new Response(error);
    }

}
