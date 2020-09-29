package com.example.rabbit.lijiang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rabbit.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Lijiang101ScrollingActivity extends AppCompatActivity {

    private final String TAG = "Lijiang101ScrollingActivity";
    private ImageView imageView101_1;
    private TextView textview101;
    private ImageView imageView101_2;
    private ImageView imageView101_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijiang101_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        imageView101_1 = findViewById(R.id.imageView101_1);
        textview101 = findViewById(R.id.textview101);
        imageView101_2 = findViewById(R.id.imageView101_2);
        imageView101_3 = findViewById(R.id.imageView101_3);
    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.menu_lijiang101_scrolling, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 点击查看张家界行程的PDF文档
     *
     * @param view
     */
    public void zhangjiajieDocsClick(View view) {
        try {
            File file = getFile();
            onNext(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onNext(File file) {
        String authority = "com.example.rabbit.fileprovider";
        Uri uriToFile = FileProvider.getUriForFile(this, authority, file);
        Intent shareIntent = new Intent(Intent.ACTION_VIEW);
        shareIntent.setDataAndType(uriToFile, "application/pdf");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.yuebaobao101:
                showYuebaobao101();
                break;
            case R.id.fatherAndMother101:
                showFatherAndMother101();
                break;
            case R.id.yanzi101:
                shoYanzi101();
                break;
            case android.R.id.home: //如果点击了返回菜单
                finish();//销毁当前的activity 并返回
                break;
        }
        return true;
    }

    private void shoYanzi101() {
        imageView101_1.setImageResource(R.drawable.yanzi_101_1);
        textview101.setText("机票信息");
        imageView101_2.setImageResource(R.drawable.yanzi_101_2);
    }

    private void showYuebaobao101() {
        imageView101_1.setImageResource(R.drawable.zhangjiajie_01);
        textview101.setText("机票信息");
        imageView101_2.setImageResource(R.drawable.zhangjiajie_04);
    }

    private void showFatherAndMother101() {
        imageView101_1.setImageResource(R.drawable.father_mother_101_1);
        textview101.setHeight(0);
        imageView101_2.setImageResource(R.drawable.father_mother_101_2);
    }

    public File getFile() throws Exception {
        try {
            // download the file
            InputStream input = getResources().openRawResource(R.raw.zhangjiajie);
            File dir = new File(getFilesDir(), "/shared_pdf");
            dir.mkdir();
            File file = new File(dir, "temp.pdf");
            OutputStream output = new FileOutputStream(file);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
