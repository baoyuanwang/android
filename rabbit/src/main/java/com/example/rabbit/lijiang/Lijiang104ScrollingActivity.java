package com.example.rabbit.lijiang;

import android.os.Bundle;

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

import com.example.rabbit.R;

public class Lijiang104ScrollingActivity extends AppCompatActivity {

    private String TAG = "Lijiang104ScrollingActivity";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijiang104_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        //获取到webView对象
        webView = findViewById(R.id.webView_104);
        //初始化webView，加载石林的行程
        initWebView(webView, "https://gs.ctrip.com/html5/you/sight/lijiang32/3056.html#ctm_ref=www_hp_bs_lst");

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
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.menu_lijiang104_scrolling, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: " + item.getTitle() + ", itemId:" + item.getItemId());

        View view = findViewById(item.getItemId());


        switch (item.getItemId()) {
            case R.id.action_jingdian104: //如果选中景点，则显示webview
                webView.setVisibility(View.VISIBLE);
                break;
            case R.id.action_huochepiao104: //如果选中火车票，则隐藏WEBVIEW
                webView.setVisibility(View.GONE);
                break;
            case android.R.id.home: //如果点击了返回菜单
                finish();//销毁当前的activity 并返回
                break;

        }
        return true;
    }

}