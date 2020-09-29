package com.example.flower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView[] imageView = new ImageView[12];
    private int[] imgPath = new int[]{
            R.mipmap.flower01,
            R.mipmap.flower02,
            R.mipmap.flower03,
            R.mipmap.flower04,
            R.mipmap.flower05,
            R.mipmap.flower06,
            R.mipmap.flower07,
            R.mipmap.flower08,
            R.mipmap.flower09,
            R.mipmap.flower10,
            R.mipmap.flower11,
            R.mipmap.flower12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid1);
        for (int i = 0; i < imageView.length; i++) {
            imageView[i] = new ImageView(MainActivity.this);
            imageView[i].setImageResource(imgPath[i]);
            imageView[i].setPadding(2, 2, 2, 2);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(316, 168);
            imageView[i].setLayoutParams(layoutParams);
            gridLayout.addView(imageView[i]);
        }

    }
}
