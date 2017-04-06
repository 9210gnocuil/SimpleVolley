package com.liucong.simplevolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static int i = 0;
    private TextView tv;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
    }

    /**
     * 处理按钮点击事件
     * @param view
     */
    public void request(View view){
        startTime = System.currentTimeMillis();
        RequestQueue requestQueue = SimpleVolley.newRequestQueue();
        StringRequest request = new StringRequest("https://www.baidu.com/", new Response.SuccessListener<String>() {
            @Override
            public void onResponse(String response) {
                if(!TextUtils.isEmpty(response));
                long delat = System.currentTimeMillis() - startTime;
                tv.setText("第"+(++i)+" 次请求成功,用时:"+delat+"ms");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onError(Throwable error) {

            }
        });

        for(int i = 0;i<10;i++){
            requestQueue.add(request);
        }

    }
}
