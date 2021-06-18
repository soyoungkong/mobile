package com.example.wepapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView wWeb=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();

    }

    private void initLayout(){
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.title_web_info));

        // WebView setting
        wWeb = (WebView) findViewById(R.id.webInfo);
        WebSettings set = wWeb.getSettings();
        set.setJavaScriptEnabled(true);

        wWeb.getSettings().setDomStorageEnabled(true);

        String url = null;
        url = String.format("https://naver.com/");
        wWeb.loadUrl(url);
    }

    class WebClient extends WebViewClient{

    }
}