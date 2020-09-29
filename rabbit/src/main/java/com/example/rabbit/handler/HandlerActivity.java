package com.example.rabbit.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.rabbit.HeadIconActivity;
import com.example.rabbit.R;

public class HandlerActivity extends AppCompatActivity {

    private TextView textView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = findViewById(R.id.tipsTextView);
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x001) {
                    textView.setText(msg.getData().getCharSequence("msg"));
                }
            }
        };
    }

    public void toNextTips(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                textView.setText("你今天的努力是幸运的伏笔，当下的付出，是明日的花开。"+System.currentTimeMillis());
                Message message = new Message();
                message.what = 0x001;
                Bundle bundle = new Bundle();
                bundle.putCharSequence("msg", "你今天的努力是幸运的伏笔，当下的付出，是明日的花开。" + System.currentTimeMillis());
                message.setData(bundle);
                handler.sendMessage(message);

            }
        }).start();
    }

    public void toFindDifferent(View view) {
        Intent intent = new Intent(HandlerActivity.this,FindDifferentActivity.class);
        startActivity(intent);
    }
}
