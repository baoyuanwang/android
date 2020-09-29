package com.example.rabbit.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rabbit.R;

import java.io.IOException;

public class SurfaceViewActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private boolean isPlaying=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(SurfaceViewActivity.this,"视频播放完毕！",Toast.LENGTH_LONG);
            }
        });
        //获取到播放和暂停按钮
        ImageButton playBtn = findViewById(R.id.surfaceViewPlay);
        ImageButton pauseBtn = findViewById(R.id.surfaceViewPause);
        //为播放按钮设置点击监听事件
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlaying){
                    play();
                    isPlaying=true;
                }
            }
        });
        //为停止按钮设置监听事件,停止视频的播放。
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    mediaPlayer.stop();
                    isPlaying=false;
                }
            }
        });
    }


    //播放视频的方法
    public void play(){
        mediaPlayer.reset();//重置medipaly对象
        mediaPlayer.setDisplay(surfaceHolder); //把视频画面输出到suerfaceHolder
        try {
            mediaPlayer.setDataSource("/sdcard/Movies/Sticker.mp4");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
}
