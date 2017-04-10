package com.bignerdranch.android.androidhttp;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hasee on 2017/4/9.
 */

public class NetUtils {

    private static final String TAG = "TAG";

    public static String post(String url,String content)
    {

        HttpURLConnection conn = null ;
        try{
            //创建一个Url对象
            URL mUrl = new URL(url);
            //调用URL的openConnection() 方法获得HttpConnection对象
            conn = (HttpURLConnection) mUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);
            //post请求参数
            String data = content;
            //获得一个输出流，想服务器写数据，默认情况下，系统不允许向服务器书写数据
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode =  conn.getResponseCode();
            if(responseCode == 200)
            {
                InputStream inputStream = conn.getInputStream();
                 String respose =  getStringFromInputStream(inputStream);
                return respose;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn != null)
            {
                conn.disconnect();
            }
        }

            return  null;
    }

        public   static  String get(String url)
        {
            HttpURLConnection connection = null;
            try{
                //利用String url 构建URL对象;
                URL mUrl = new URL(url);
                connection = (HttpURLConnection) mUrl.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(10000);

                int responseCode = connection.getResponseCode();

                Log.i("TAG", "get: ");
                if(responseCode >= 200 && responseCode < 300)
                {
                    Log.i("TAG", "resp ");
                    InputStream is = connection.getInputStream();
                    String respose = getStringFromInputStream(is);
                    return  respose ;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(connection!= null)
                {
                    connection.disconnect();
                }
            }

        return  null;
        }


    private static  String getStringFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Log.i("TAG", "getStringFromInputStream: ");
        byte [] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1){
            Log.i("TAG", "getStringFromInputStream: ");
            os.write(buffer,0,len);
            os.flush();
        }
        inputStream.close();
        String state = os.toString();
        os.close();
        return  state ;



    }
}
