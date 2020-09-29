package com.example.rabbit.recyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.rabbit.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<Fruit> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        //获取到recycleView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //初始化数据
        initData();
        //recyclerveiw 是需要设置样式的，也就是设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置数据到recyclerView中去
        recyclerView.setAdapter(new FruitAdapter(fruits));

    }

    private void initData() {
        fruits = new ArrayList<Fruit>();
        for (int i = 0; i < Datas.icons.length; i++) {
            Fruit fruit = new Fruit(Datas.title[i], Datas.icons[i]);
            fruits.add(fruit);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setIcon(R.drawable.chat1bg);
        supportActionBar.setTitle("Recycler View 效果测试");
        // getActionBar().setTitle("Recycler View 效果测试");
        return super.onCreateOptionsMenu(menu);
    }
}
