package com.bignerdranch.android.androidhttp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private TextView  textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.http_text);
        AsynNetUitls.get("https://www.baidu.com", new AsynNetUitls.Callback() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        });
   /*     new Thread(new Runnable() {
            @Override
            public void run() {
                //从网络获取资源
                final String response  =  NetUtils.get("http://www.baidu.com");
                //Hander 发送处理操作
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //在主线程中更新Ui
                        textView.setText(response);
                        if (response == null)
                        {
                            textView.setText("23333333");
                        }
                    }
                });
            }
        }).start();*/
    }
}
