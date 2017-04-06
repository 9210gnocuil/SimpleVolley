package com.liucong.simplevolley;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by liucong on 2017/4/6.
 * 策略模式中的具体策略
 */

public class HttpURLConnectionStack implements HttpStack {

    @Override
    public NetworkResponse performRequest(Request<?> request) throws IOException {

        String urlStr = request.getUrl();
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while((len = bis.read(buf))!=-1){
                baos.write(buf,0,len);
            }

            connection.disconnect();
            inputStream.close();
            bis.close();
            baos.close();

            byte[] responseContent = baos.toByteArray();
            return new NetworkResponse(responseContent,responseCode);
        }

        return null;
    }
}
