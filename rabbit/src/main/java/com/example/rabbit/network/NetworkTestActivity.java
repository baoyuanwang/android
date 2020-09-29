package com.example.rabbit.network;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rabbit.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkTestActivity extends AppCompatActivity {


    private TextView urlTextview;
    private TextView responseTextview;
    private Button button;
    private String TAG = "NetworkTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test);
        //获取到button对象
        button = findViewById(R.id.sendRequest);
        //获取到输入网址的textview和响应的textView
        urlTextview = findViewById(R.id.UrlTextView);
        responseTextview = findViewById(R.id.ResonseTextView);
        //设置对象的监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sendRequestWithUrlConnection(); //（1）使用安卓自带的HttpURLClient 发送请求
                //sendReqeustWithOkHttp();//（2）使用OK HTTP发送网络请求
                HttpUtil.sendHttpRequest(urlTextview.getText().toString(), new HttpCallbackListener() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "network 返回消息rsp: " + response);
                        showResponse(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, "onError: ", e);
                    }
                }); //使用工具类


            }
        });
    }

    /**
     * 使用OK HTTP完成网络请求
     */
    private void sendReqeustWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(8000, TimeUnit.MILLISECONDS)
                        .readTimeout(8000, TimeUnit.MILLISECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(urlTextview.getText().toString())
                        .build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String res = response.body().string();
                    showResponse(res);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 使用安卓自带的HTTPURLConnection 对象完成网络请求
     */
    private void sendRequestWithUrlConnection() {
        //开启新的线程执行网络请求操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(urlTextview.getText().toString());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //对获取到的输入流进行读取
                    StringBuilder sb = new StringBuilder();
                    byte[] buffer = new byte[4096];
                    while (in.read(buffer) != -1) {
                        sb.append(new String(buffer));
                    }
                    Log.e(TAG, "run: " + sb.toString());
                    //Thread.sleep(3000);
                    //responseTextview.setText(sb.toString());
                    showResponse(sb.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String sb) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseTextview.setText(sb);
            }
        });
    }
}