package com.example.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChatMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_msg);
        TextView msgshow = findViewById(R.id.msgshow);
        msgshow.setText(getIntent().getExtras().getCharSequence("msg"));
        //这条好像设置了没啥用boolean: true to show the user that selecting home will
        // return one level up rather than to the top level of the app.
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
