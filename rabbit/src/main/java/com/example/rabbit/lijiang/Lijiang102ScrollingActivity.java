package com.example.rabbit.lijiang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.example.rabbit.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Lijiang102ScrollingActivity extends AppCompatActivity {

    private String TAG = "Lijiang102ScrollingActivity";
    private Toolbar toolbar;
    private Button btn_10_2_toHuochepiao;
    private Button btn_10_2_toJingdian;
    private ImageView imageView;
    private WebView webView;
    private int height;
    private View btn_10_2_toHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijiang102_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置toolbar的onclick事件，点击后关闭当前的activity.
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "NavigationOnClick: ");
//                finish();
//            }
//        });
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.webView_102);
        imageView = findViewById(R.id.imageView_huochepiao10_2);
        //初始化webView，加载石林的行程
        initWebView(webView);
        //设置火车票的事件监听器，如果点击了则隐藏webview
        btn_10_2_toHuochepiao = findViewById(R.id.btn_10_2_toHuochepiao);
        btn_10_2_toHuochepiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.setVisibility(View.GONE);//设置行程不可见
                imageView.setImageDrawable(getDrawable(R.drawable.huochepiao10_2));//替换图片
            }
        });
        //设置行程的事件监听器，如果点击了则隐藏webview
        btn_10_2_toJingdian = findViewById(R.id.btn_10_2_toJingdian);
        btn_10_2_toJingdian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置行程可见
                webView.setVisibility(View.VISIBLE);
//                //设置Webview的高度为0；
//                ViewGroup.LayoutParams linearParams2 = (ViewGroup.LayoutParams) webView.getLayoutParams();
//                if (height != 0) {
//                    linearParams2.height = height;
//                }
//                webView.setLayoutParams(linearParams2);

            }
        });
        //设置点击酒店的事件监听器
        btn_10_2_toHotel = findViewById(R.id.btn_10_2_toHotel);
        btn_10_2_toHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.setVisibility(View.GONE);//设置行程不可见
                imageView.setImageDrawable(getDrawable(R.drawable.hotel_102_1));//替换图片
            }
        });
        //记录日志
        Log.d(TAG, "onCreate: " + webView.getUrl());
    }

    private void initWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        // webView.getSettings().setLoadWithOverviewMode(true);
        // webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://m.ctrip.com/webapp/things-to-do/detail/18761314");
    }

    public void onNext(File file) {
        String authority = "com.example.rabbit.fileprovider";
        Uri uriToFile = FileProvider.getUriForFile(Lijiang102ScrollingActivity.this, authority, file);
        Intent shareIntent = new Intent(Intent.ACTION_VIEW);
        shareIntent.setDataAndType(uriToFile, "application/pdf");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
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
