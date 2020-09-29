package com.example.rabbit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //设置返回按钮，销毁掉当前的activity.
        Button button = findViewById(R.id.goBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置发送按钮，通过INTENT传递消息.
        Button sendMsg = findViewById(R.id.sendMsg);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, ChatMsgActivity.class);
                Bundle bundle = new Bundle();
                EditText inputMsg = findViewById(R.id.inputMsg);
                bundle.putCharSequence("msg", inputMsg.getText());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //创建头像监听器对象
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, HeadIconActivity.class);
                Log.i("view.getId()", "" + view.getId());
                String tag = (String) view.getTag();
                startActivityForResult(intent, Integer.parseInt(tag));
            }
        };
        findViewById(R.id.avatar1).setOnClickListener(onClickListener);
        findViewById(R.id.avatar2).setOnClickListener(onClickListener);
        findViewById(R.id.avatar3).setOnClickListener(onClickListener);
        findViewById(R.id.avatar4).setOnClickListener(onClickListener);

        //监听通讯录对象
        findViewById(R.id.chatcontacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChatActivity.this,ChatContactActivity.class);
                startActivity(intent);
            }
        });

        //监听音乐图像
        findViewById(R.id.chatmusic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChatActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }

    //点击头像更换头像
    public void changeAvatar(View view) {
        Intent intent = new Intent(ChatActivity.this, HeadIconActivity.class);
        startActivityForResult(intent, 0x007);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x007) {
            int iconId = data.getIntExtra("iconId", R.mipmap.head1);
            ImageView imageView = null;
            Log.i("requestCode:",""+requestCode);
            switch (requestCode) {
                case 1:
                    imageView = findViewById(R.id.avatar1);
                    break;
                case 2:
                    imageView = findViewById(R.id.avatar2);
                    break;
                case 3:
                    imageView = findViewById(R.id.avatar3);
                    break;
                case 4:
                    imageView = findViewById(R.id.avatar4);
                    break;
            }
            imageView.setImageResource(iconId);
        }
    }
}
