package com.example.rabbit.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rabbit.R;

public class ContactReaderActivity extends AppCompatActivity {

    private  String DISPLAY_NAME= ContactsContract.Contacts.DISPLAY_NAME;
    private  String NUMBER= ContactsContract.CommonDataKinds.Phone.NUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_reader);
        TextView textView = findViewById(R.id.contactReader);
        textView.setText(getQueryData());
    }

    private CharSequence getQueryData(){
        StringBuilder sb=new StringBuilder();
        //查询记录
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null);
        int columnIndex_DISPLAY_NAME = cursor.getColumnIndex(DISPLAY_NAME);//获得姓名对象的索引值
        int columnIndex_NUMBER = cursor.getColumnIndex(NUMBER);//获得电话号码对象的索引值
        while(cursor.moveToNext()){
            String displayName= cursor.getString(columnIndex_DISPLAY_NAME);
            String Number= cursor.getString(columnIndex_NUMBER);
            Log.i("读取的联系人信息","-----------displayName------------"+displayName);
            sb.append(displayName+"  "+Number+"\n");
        }
        cursor.close();
        Log.i("读取的联系人信息","-----------------------"+sb.toString());
        return sb.toString();
    }
}
