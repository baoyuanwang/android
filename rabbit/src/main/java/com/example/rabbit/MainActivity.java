package com.example.rabbit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rabbit.audioplayer.AudioPlayerActivity;
import com.example.rabbit.audioplayer.SurfaceViewActivity;
import com.example.rabbit.audioplayer.VideoViewActivity;
import com.example.rabbit.broadcastreciver.BroadcastActivity;
import com.example.rabbit.contentprovider.ContactReaderActivity;
import com.example.rabbit.handler.HandlerActivity;
import com.example.rabbit.lijiang.LijiangActivity;
import com.example.rabbit.network.NetworkTestActivity;
import com.example.rabbit.phonecall.PhoneCallActivity;
import com.example.rabbit.recyclerview.Fruit;
import com.example.rabbit.recyclerview.RecyclerActivity;
import com.example.rabbit.service.boundservice.DoubleColorBallActivity;
import com.example.rabbit.sqlLitedatabase.SQLiteDBActivity;
import com.example.rabbit.webview.WebviewActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Fruit> fruits = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout frameLayout = findViewById(R.id.rabbit);
        final RabbitView rabbitView = new RabbitView(this);
        rabbitView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rabbitView.ratbbitX = motionEvent.getX();
                rabbitView.ratbbitY = motionEvent.getY();
                //重新绘制小兔子
                rabbitView.invalidate();
                return false;
            }
        });

        Button button = findViewById(R.id.toRelative);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RelativeActivity.class);
                startActivity(intent);
            }
        });
//        frameLayout.addView(rabbitView);

        Button toLinear = findViewById(R.id.toLeanerLayout);
        toLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LeanerActivity.class);
                startActivity(intent);
            }
        });

        Button toFrameLayout = findViewById(R.id.toFrameLayout);
        toFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        //给微信聊天按钮增加事件监听器
        Button toChatButton = findViewById(R.id.toChatButton);
        toChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        //给进度条按钮增加事件监听器
        Button toProgressBar = findViewById(R.id.toProgressBar);
        toProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProgressBarActivity.class);
                startActivity(intent);
            }
        });


        //把自定义的RABBIT View添加到视图中
        frameLayout.addView(rabbitView);
    }


    // 点击to Recycler View 执行的方法
    public void toRecyclerView(View view) {
        Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
        startActivity(intent);
    }
    // 点击to bound Service  执行的方法
    public void toBoundServcie(View view) {
        Intent intent = new Intent(MainActivity.this, DoubleColorBallActivity.class);
        startActivity(intent);
    }
    // 点击to toContentProvider  执行的方法
    public void toContentProvider(View view) {
        Intent intent = new Intent(MainActivity.this, ContactReaderActivity.class);
        startActivity(intent);
    }
    // 点击to Handler  执行的方法
    public void toHandler(View view) {
        Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
        startActivity(intent);
    }
    // 点击to broadcast receiver  执行的方法
    public void toBroadCastReceiver(View view) {
        Intent intent=new Intent(MainActivity.this, BroadcastActivity.class);
        startActivity(intent);
    }
    // 点击音频播放执行的方法
    public void toAudioPlayer(View view) {
        Intent intent=new Intent(MainActivity.this, AudioPlayerActivity.class);
        startActivity(intent);

    }

    // 点击视频播放执行的方法
    public void toVideoPlayer(View view) {
        Intent intent=new Intent(MainActivity.this, VideoViewActivity.class);
        startActivity(intent);
    }

    // 点击surfaceView进行视频播放
    public void toSurfaceViewVideoPlayer(View view) {
        Intent intent=new Intent(MainActivity.this, SurfaceViewActivity.class);
        startActivity(intent);
    }

    // 点击surfaceView进行视频播放
    public void toMakePHoneCall(View view) {
        Intent intent=new Intent(MainActivity.this, PhoneCallActivity.class);
        startActivity(intent);
    }

    // 点击创建数据库按钮执行的操作
    public void toSQLiteDB(View view) {
        Intent intent=new Intent(MainActivity.this, SQLiteDBActivity.class);
        startActivity(intent);
    }

    // 点击webview按钮执行的操作
    public void toWebview(View view) {
        Intent intent=new Intent(MainActivity.this, WebviewActivity.class);
        startActivity(intent);
    }
    // 点击我在丽江等你呀
    public void toLijiang(View view) {
        Intent intent=new Intent(MainActivity.this, LijiangActivity.class);
        startActivity(intent);
    }
    // 点击网络测试程序执行的操作
    public void toNetworkTest(View view) {
        Intent intent=new Intent(MainActivity.this, NetworkTestActivity.class);
        startActivity(intent);
    }

    // 点击进入微软的adaptiveCard  test方法
    public void toAdaptiveCard(View view) {
        Intent intent=new Intent(MainActivity.this, AdaptiveCardActivity.class);
        startActivity(intent);
    }
}
