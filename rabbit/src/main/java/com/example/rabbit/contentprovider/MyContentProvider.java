package com.example.rabbit.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.rabbit.sqlLitedatabase.MyDatabaseHelper;

public class MyContentProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.rabbit.contentprovider";
    public static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }


    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        String type = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                type = "vnd.android.cursor.dir/vnd."+AUTHORITY+".book";
                break;
            case BOOK_ITEM:
                type = "vnd.android.cursor.dir/vnd."+AUTHORITY+".book";
                break;
            case CATEGORY_DIR:
                type = "vnd.android.cursor.dir/vnd."+AUTHORITY+".category";
                break;
            case CATEGORY_ITEM:
                type = "vnd.android.cursor.dir/vnd."+AUTHORITY+".category";
                break;
            default:
                break;
        }
        return type;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Uri returnUrl = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                long bookId = db.insert("BOOK", null, values);
                returnUrl = Uri.parse("content://" + AUTHORITY + "/book/" + bookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long categoryId = db.insert("category", null, values);
                returnUrl = Uri.parse("content://" + AUTHORITY + "/category/" + categoryId);
                break;
            default:
                break;
        }
        return returnUrl;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(), "BOOKSTORE.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("BOOK", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                cursor = db.query("BOOK", projection, "id=?", new String[]{bookid}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categryId = uri.getPathSegments().get(1);
                cursor = db.query("category", projection, "id=?", new String[]{categryId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int updaterows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updaterows = db.delete("BOOK",selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                updaterows = db.delete("BOOK", "id=?", new String[]{bookid});
                break;
            case CATEGORY_DIR:
                updaterows = db.delete("category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updaterows = db.delete("category",  "id=?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return updaterows;
    }
}
