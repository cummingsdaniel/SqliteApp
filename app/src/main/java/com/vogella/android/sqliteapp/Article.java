package com.vogella.android.sqliteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;


public class Article extends Activity {
    WebView articleWebView;
    ProgressBar loader;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsapp_articale_activity);
//        Button addToFavs = (Button) findViewById(R.id.add_to_favs);
//        addToFavs.setOnClickListener( f ->{
//        });
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        loader = findViewById(R.id.loader);
        articleWebView = findViewById(R.id.webView);
        Log.d("noooooo", url);
        articleWebView.getSettings().setJavaScriptEnabled(true);
        articleWebView.getSettings().setLoadWithOverviewMode(true);
        articleWebView.getSettings().setUseWideViewPort(true);
//        webView.loadUrl(url);
        articleWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loader.setVisibility(View.INVISIBLE);
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                loader.setVisibility(View.INVISIBLE);
            }
        });

        articleWebView.loadUrl(url);

    }
}