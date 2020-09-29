package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button insertBtn = findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.rabbit.contentprovider/book");
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name", "The DA Vinci CODE" + new Random().nextInt(30));
                values.put("author", "Dan Brown");
                values.put("pages", 454 + new Random().nextInt(100));
                values.put("price", 19.68 + new Random().nextInt(100));
                Uri newUri = getContentResolver().insert(uri, values);
            }
        });

        Button queryBtn = findViewById(R.id.queryBtn);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.rabbit.contentprovider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "NAME: " + name + ", " + author + "," + pages + ", " + price + "\n");
                    }
                }
            }
        });
        Button updateBtn = findViewById(R.id.updateBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
    }
}
