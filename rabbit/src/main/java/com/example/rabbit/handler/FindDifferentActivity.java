package com.example.rabbit.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rabbit.R;

public class FindDifferentActivity extends AppCompatActivity {

    private ProgressBar pb;
    private Handler handler;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_different);
        pb = findViewById(R.id.findDifferentProgressBar);
        progress = 100;
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x001) {
                    pb.setProgress(msg.getData().getInt("progress"));
                    Toast.makeText(FindDifferentActivity.this, "剩余时间：" + progress, Toast.LENGTH_SHORT).show();
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress > 0) {
                    Message msg = Message.obtain();
                    msg.what = 0x001;
                    Bundle bundle = new Bundle();
                    bundle.putInt("progress", progress--);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }
        }).start();

    }
}
