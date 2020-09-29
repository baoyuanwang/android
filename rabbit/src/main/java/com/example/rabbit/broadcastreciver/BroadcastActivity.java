package com.example.rabbit.broadcastreciver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.rabbit.R;

public class BroadcastActivity extends AppCompatActivity {
    private String TAG = "BroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
//        BroadcastReceiver myReceiver = new MyReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("zuckerberg");
//        intentFilter.addAction("mayun");
//        this.registerReceiver(myReceiver, intentFilter);
    }

    public void sendBroadcast(View view) {
        //发送第一个广播消息
        Log.d(TAG, "开始执行BroadcastActivity的点击事件");
        Intent intent = new Intent();
        intent.setAction("zuckerberg");
        sendBroadcast(intent);
        //发送第二个广播消息
        Intent intent2 = new Intent();
        intent2.setAction("com.example.broadcast.MY_NOTIFICATION");
        intent2.putExtra("data", "Notice me senpai!");
        sendBroadcast(intent2);
    }
}
