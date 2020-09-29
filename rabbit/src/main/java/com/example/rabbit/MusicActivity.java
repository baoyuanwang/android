package com.example.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.rabbit.service.MusicService;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
    }

    public void playMusic(View view) {
        Intent intent=new Intent(MusicActivity.this, MusicService.class);
        if(!MusicService.isplay){
            startService(intent);
            ImageView imageView = findViewById(R.id.musicPlay);
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.head1,null));
        }else{
            stopService(intent);
            ImageView imageView = findViewById(R.id.musicPlay);
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.music,null));
        }
        //findViewById();
    }

    @Override
    protected void onStart() {
        Intent intent=new Intent(MusicActivity.this, MusicService.class);
        startService(intent);
        super.onStart();
    }
}
