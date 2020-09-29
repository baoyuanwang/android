package com.example.rabbit.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    final static String TAG = "HttpUtil";

    public static void sendHttpRequest(final String address, final HttpCallbackListener callbackListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //采用安卓自带的httpUrlconnection方法
                HttpURLConnection connection = null;
                StringBuilder sb = new StringBuilder();
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true); //设置从服务器获取数据流，因此设置为true，不设置默认为true.
                    connection.setDoOutput(true); //设置是否要给服务器输出内容，GET请求设置为false,post设置为true， 默认为true.
                    InputStream in = connection.getInputStream();
                    //对获取到的输入流进行读取
                    byte[] buffer = new byte[4096]; //设置缓冲区的大小，先将输入流读到缓冲区
                    while (in.read(buffer) != -1) {
                        sb.append(new String(buffer));
                    }
                    if (callbackListener != null) {
                        callbackListener.onSuccess(sb.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    callbackListener.onError(e);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
        Log.d(TAG, "HttpUtil.sendHttpRequest: 执行完毕");
    }

    public static void sendOkHttpRequest(final String address, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(address)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
