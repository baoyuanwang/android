package com.example.rabbit.lijiang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rabbit.R;

public class LijiangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijiang);
    }

    public void btn930Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang930Activity.class);
        startActivity(intent);
    }

    public void btn101Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang101ScrollingActivity.class);
        startActivity(intent);
    }

    public void btn102Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang102ScrollingActivity.class);
        startActivity(intent);
    }

    public void btn103Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang103ScrollingActivity.class);
        startActivity(intent);
    }

    public void btn104Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang104ScrollingActivity.class);
        startActivity(intent);
    }

    public void btn105Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang105ScrollingActivity.class);
        startActivity(intent);
    }

    public void btn106Click(View view) {
        Intent intent=new Intent(LijiangActivity.this,Lijiang106ScrollingActivity.class);
        startActivity(intent);
    }
}
