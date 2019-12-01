package com.vogella.android.sqliteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class Article extends Activity {
    WebView articleWebView;
    ProgressBar progressBar;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsapp_articale_activity);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        progressBar = findViewById(R.id.loader);
        articleWebView = findViewById(R.id.webView);
        Log.d("noooooo", url);
        articleWebView.getSettings().setJavaScriptEnabled(true); /*sets the webView to enable Javascript Execution*/
        articleWebView.getSettings().setLoadWithOverviewMode(true); /*sets web content to fit width wise on screen*/

        articleWebView.setWebViewClient(new WebViewClient() {



            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.INVISIBLE);
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        articleWebView.loadUrl(url);

    }
}