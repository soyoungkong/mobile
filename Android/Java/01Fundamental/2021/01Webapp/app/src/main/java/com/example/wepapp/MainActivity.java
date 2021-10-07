package com.example.wepapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
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
        // webView 가져오기
        wWeb = (WebView) findViewById(R.id.webInfo);
        WebSettings set = wWeb.getSettings();
        // java script 활성화
        set.setJavaScriptEnabled(true);

        // WebChromeClient 설정 - 브라우저 이벤트 구현을 위해 필요
        wWeb.setWebChromeClient(new WebChromeClient());
        // WebViewClient 설정 - 새 페이지를 띄울 때 새 창이 아닌 현재 view에서 띄우기 위해 필요
        wWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 새 페이지를 띄울 때 현재 view에서 띄우도록 설정
                view.loadUrl(url);
                return true;
            }
        });


        String url = null;
        url = String.format("https://naver.com/");
        // 웹 페이지 띄우기
        wWeb.loadUrl(url);
    }

    /**
     * 웹 브라우저 ‘뒤로가기’ 효과
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK)
            return super.onKeyDown(keyCode, event);

        if (wWeb.canGoBack()) {
            wWeb.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}