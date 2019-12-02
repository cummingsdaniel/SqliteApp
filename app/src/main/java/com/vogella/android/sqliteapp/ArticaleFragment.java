package com.vogella.android.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ArticaleFragment extends Fragment {
    public Article article;
    private boolean isTablet;
    private Bundle dataFromNewListMain;
    private String url;
    private ProgressBar progressBar;
    public void setTablet(boolean tablet) {
        isTablet = tablet;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataFromNewListMain = getArguments();
        url = dataFromNewListMain.getString(NewsMain.KEY_URL);

        View result = inflater.inflate(R.layout.newsapp_articale_activity, container, false);


        WebView articleWebView = (WebView) result.findViewById(R.id.webView);
        Log.d("noooooo", url);
        articleWebView.getSettings().setJavaScriptEnabled(true); /*sets the webView to enable Javascript Execution*/
        articleWebView.getSettings().setLoadWithOverviewMode(true); /*sets web content to fit width wise on screen*/
        articleWebView.loadUrl(url);
        articleWebView.setWebViewClient(new WebViewClient() {


            /*sets the visability of the progress bar in front of webview*/
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.INVISIBLE);
                view.loadUrl(url);
                return true;
            }

            /*sets the visability of the progress bar to Invisible on finished page */
            @Override
            public void onPageFinished(WebView view, final String url) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return result;
    }

}
