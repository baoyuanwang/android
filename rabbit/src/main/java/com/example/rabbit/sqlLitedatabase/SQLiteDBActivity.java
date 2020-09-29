package com.example.rabbit.sqlLitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rabbit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SQLiteDBActivity extends AppCompatActivity {

    MyDatabaseHelper myDatabaseHelper;
    private String TAG = "SQLiteDBActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_db);
        myDatabaseHelper = new MyDatabaseHelper(SQLiteDBActivity.this, "BOOKSTORE.db", null, 2);
        Button button = findViewById(R.id.createDB_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getWritableDatabase();
            }
        });

        //给插入按钮注册监听器
        Button insertDatabtn = findViewById(R.id.insertDatabtn);
        insertDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                List<ContentValues> list = getContentValues();
                for (ContentValues values: list  ) {
                    db.insert("Book", null, values);
                }
                Toast.makeText(SQLiteDBActivity.this, "插入"+list.size()+"条记录成功", Toast.LENGTH_LONG).show();

            }
        });

        //给更新按钮注册监听器
        Button updateDatabtn = findViewById(R.id.updateDatabtn);
        updateDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装更新的内容
                values.put("price", "11.68");
                db.update("Book", values, "name=?", new String[]{"The DA Vinci CODE"});
                Toast.makeText(SQLiteDBActivity.this, "修改The DA Vinci CODE的价格成功", Toast.LENGTH_LONG).show();

            }
        });
        //给删除按钮注册监听器
        Button deleteDatabtn = findViewById(R.id.deleteDatabtn);
        deleteDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装更新的内容
                db.delete("Book", " name = ? ", new String[]{"The DA Vinci CODE"});
                Toast.makeText(SQLiteDBActivity.this, "删除The DA Vinci CODE成功", Toast.LENGTH_LONG).show();
            }
        });

        //给清空按钮注册监听器
        Button clearDatabtn = findViewById(R.id.clearDatabtn);
        clearDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                int rows = db.delete("BOOK", null, null);
                Toast.makeText(SQLiteDBActivity.this, "清空数据库成功，受影响的行数为" + rows, Toast.LENGTH_LONG).show();
            }
        });

        //给查询按钮注册监听器
        Button queryDatabtn = findViewById(R.id.queryDatabtn);
        queryDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("BOOK", null, null, null, null, null, null);
                if (cursor.moveToNext()) {
                    do {
                        Log.d(TAG, cursor.getString(cursor.getColumnIndex("NAME")) + "," + cursor.getString(cursor.getColumnIndex("PRICE")) + "\n");
                    }
                    while (cursor.moveToNext());
                }
            }
        });
    }

    private List<ContentValues> getContentValues() {
        List<ContentValues> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            //开始组装第一条数据
            values.put("name", "The DA Vinci CODE" + new Random().nextInt(30));
            values.put("author", "Dan Brown");
            values.put("pages", 454 + new Random().nextInt(100));
            values.put("price", 19.68 + new Random().nextInt(100));
            list.add(values);
        }
        return list;
    }
}
