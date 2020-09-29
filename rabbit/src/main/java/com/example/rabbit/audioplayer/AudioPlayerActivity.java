package com.example.rabbit.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rabbit.R;

import java.io.File;
import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private final String TAG = "AudioPlayerActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        File file = new File("/sdcard/music-1.mp3");
        Log.d(TAG, "文件是否存在：" + file.exists());
        Log.d(TAG, "文件是否可读：" + file.canRead());
        if (file.exists()) {
            Log.d(TAG, "文件Uri.parse：" + file.getAbsolutePath());
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(file));
        } else {
            Toast.makeText(this, "播放的音频文件不存在", Toast.LENGTH_LONG);
            return;
        }

        //获取到播放按钮，并为其注册播放事件
        ImageButton playButton = findViewById(R.id.toMusic_play_btn);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "playButton 被点击了，音乐时长为:" + mediaPlayer.getDuration());
                if (!isPlaying) {
                    Log.d(TAG, "playButton 被点击了，音乐开始播放。。。。。。。。" );
                    mediaPlayer.start();
                    isPlaying = true;
                }
            }
        });
        //获取到暂停按钮，并为其注册播放事件
        ImageButton pauseButton = findViewById(R.id.toMusic_pause_btn);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "pauseButton 被点击了，isplaying is :" + isPlaying);
                if (isPlaying) {
                    mediaPlayer.pause();
                    isPlaying = false;
                }
            }
        });
    }
}
