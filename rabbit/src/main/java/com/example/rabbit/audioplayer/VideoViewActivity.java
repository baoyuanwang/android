package com.example.rabbit.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.rabbit.R;

import java.io.File;

public class VideoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);
        VideoView videoView = findViewById(R.id.videoView);
        //加载要播放的视频

        File file = new File("/sdcard/Movies/Sticker.mp4");
        if (file.exists()) {
            videoView.requestFocus();
            videoView.setVideoPath("/sdcard/Movies/Sticker.mp4");
        } else {
            Toast.makeText(VideoViewActivity.this, "播放的文件不存在！", Toast.LENGTH_LONG);
            return;
        }
        //创建视频控制对象
        MediaController mediaController=new MediaController(VideoViewActivity.this);
        mediaController.setEnabled(true);
        mediaController.setSoundEffectsEnabled(true);
        videoView.setMediaController(mediaController);
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(VideoViewActivity.this, "视频播放完成！", Toast.LENGTH_LONG);
            }
        });

    }
}
