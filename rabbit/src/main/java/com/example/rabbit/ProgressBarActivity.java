package com.example.rabbit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressBarActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int mProgress = 0;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x111) {
                    progressBar.setProgress(mProgress);
                } else {
                    Toast.makeText(ProgressBarActivity.this, "耗时操作已经完成！", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProgress = doWork();
                    Message message = new Message();
                    if (mProgress < 100) {
                        message.what = 0x111;
                        handler.sendMessage(message);
                    } else {
                        message.what = 0x100;
                        handler.sendMessage(message);
                        break;
                    }

                }
            }
        }).start();

    }

    private int doWork() {
        try {
            mProgress += Math.random() * 10;
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mProgress;
    }
}