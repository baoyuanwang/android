package com.example.rabbit.sqlLitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final String CREATE_BOOK =
            "create table BOOK(" +
                    "ID integer primary key autoincrement," +
                    "AUTHOR text," +
                    "PRICE real," +
                    "PAGES integer," +
                    "NAME text)";

    private final String CREATE_CATEGORY =
            "create table Category(" +
                    "ID integer primary key autoincrement," +
                    "name text," +
                    "code integer)";

    private final Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "图书数据库创建成功！", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists BOOK");
        db.execSQL("drop table if exists  Category");
        onCreate(db);
    }
}
