package com.example.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadIconActivity extends AppCompatActivity {

    private int[] picture = new int[]{
            R.mipmap.head1, R.mipmap.head2, R.mipmap.head3, R.mipmap.head4, R.mipmap.head5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_icon);
        GridView gridView = findViewById(R.id.gridview);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return picture.length;
            }

            @Override
            public Object getItem(int i) {
                return i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ImageView imageView = null;
                if (view == null) {
                    imageView = new ImageView(HeadIconActivity.this);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxWidth(158);
                    imageView.setMaxHeight(158);
                    imageView.setPadding(5, 5, 5, 5);
                } else {
                    imageView = (ImageView) view;
                }
                imageView.setImageResource(picture[i]);
                return imageView;
            }
        };
        gridView.setAdapter(baseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=getIntent();
                intent.putExtra("iconId",picture[i]);
                setResult(0x007,intent);
                finish();
            }
        });
    }
}
