package com.example.rabbit.lijiang;

import android.os.Bundle;

import com.example.rabbit.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Lijiang105ScrollingActivity extends AppCompatActivity {
    private String TAG = "Lijiang105ScrollingActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijiang105_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        //获取到webView对象
        webView = findViewById(R.id.webView_105);
        //初始化webView，加载大理一日游的行程
        initWebView(webView, "https://m.ctrip.com/webapp/things-to-do/detail/17862379/");
    }

    private void initWebView(WebView webView, String url) {
        Log.d(TAG, "initWebView: ");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lijiang105_scrolling,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: " + item.getTitle() + ", itemId:" + item.getItemId());
        View view = findViewById(item.getItemId());
        switch (item.getItemId()) {
            case R.id.action_jingdian105: //如果选中景点，则显示webview
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.action_huochepiao105: //如果选中火车票，则隐藏WEBVIEW
                webView.setVisibility(View.GONE);
                break;
            case android.R.id.home: //如果点击了返回菜单
                Log.d(TAG, "onOptionsItemSelected: 返回按钮android.R.id.home被点击了");
                finish();//销毁当前的activity 并返回
                break;
        }
        return true;
    }

}