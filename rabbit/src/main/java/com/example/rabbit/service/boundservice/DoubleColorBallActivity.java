package com.example.rabbit.service.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.example.rabbit.R;

import java.util.ArrayList;
import java.util.List;

public class DoubleColorBallActivity extends AppCompatActivity {

    BoundService boundService; //什么service对象

    int[] txids=new int[]{
            R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.textView6, R.id.textView7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_color_ball);
        Intent intent=new Intent(this,BoundService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boundService= ((BoundService.MyBinder)iBinder).getBoundService();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void generateRamdomNum(View view) {
        List<String> list=boundService.getRandomNumber();
        for (int i = 0; i <list.size() ; i++) {
            TextView textView = findViewById(txids[i]);
            textView.setText(list.get(i));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(conn);
    }
}
