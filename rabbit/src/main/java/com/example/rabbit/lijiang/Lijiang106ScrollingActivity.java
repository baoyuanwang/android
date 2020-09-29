package com.example.rabbit.lijiang;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.rabbit.R;

public class Lijiang106ScrollingActivity extends AppCompatActivity {

    private String TAG = "Lijiang106ScrollingActivity";
    private ImageView imageView_106_1;
    private ImageView imageView_106_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijiang106_scrolling);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        //获取到2个ImageView对象
        imageView_106_1 = findViewById(R.id.imageView_106_1);
        imageView_106_2 = findViewById(R.id.imageView_106_2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lijiang106_scrolling, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: " + item.getTitle() + ", itemId:" + item.getItemId());
        View view = findViewById(item.getItemId());
        switch (item.getItemId()) {
            case R.id.action_airline106_1: //如果悦宝宝，则呈现悦宝宝的机票信息
                imageView_106_1.setVisibility(View.VISIBLE);
                break;
            case R.id.action_airline106_2: //如果爸妈，则呈现爸妈的机票信息
                imageView_106_1.setVisibility(View.GONE);
                break;
            case android.R.id.home: //如果点击了返回菜单
                Log.d(TAG, "onOptionsItemSelected: 返回按钮android.R.id.home被点击了");
                finish();//销毁当前的activity 并返回
                break;
        }
        return true;
    }
}