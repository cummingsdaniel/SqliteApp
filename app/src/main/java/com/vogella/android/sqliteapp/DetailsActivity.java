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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by SHAJIB-PC on 9/9/2019.
 */

public class DetailsActivity extends Activity {
    WebView webView;
    //ProgressBar loader;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Button addToFavs = (Button) findViewById(R.id.add_to_favs);
//        addToFavs.setOnClickListener( f ->{
//        });
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        // = findViewById(R.id.loader);
        webView = findViewById(R.id.webView);
        Log.d("noooooo", url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(url);
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                loader.setVisibility(View.INVISIBLE);
//                view.loadUrl(url);
//
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, final String url) {
//                loader.setVisibility(View.INVISIBLE);
//            }
 //       });

        //webView.loadUrl(url);

    }
}