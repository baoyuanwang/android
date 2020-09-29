package com.example.rabbit.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.rabbit.R;

public class MusicService extends Service {

    public static boolean isplay = false;
    MediaPlayer mediaPlayer = null;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //创建音频对象
        mediaPlayer=MediaPlayer.create(this, R.raw.music_1);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            isplay=mediaPlayer.isPlaying();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //停止音乐的播放
        mediaPlayer.stop();
        isplay=mediaPlayer.isPlaying();
        mediaPlayer.release();
        super.onDestroy();
    }
}
