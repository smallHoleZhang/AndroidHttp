package com.bignerdranch.android.androidhttp;

import android.os.Handler;

/**
 * Created by hasee on 2017/4/10.
 */

public class AsynNetUitls {
    public interface Callback{
        void onResponse(String response);
    }

    public static void get (final String url,final Callback callback)
    {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final  String respose = NetUtils.get(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(respose);
                    }
                });
            }
        }).start();
    }
}
