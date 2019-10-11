package com.fossourier.nicolas.mynews.Controllers.Activities;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fossourier.nicolas.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// Webview for display the article clicked
public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view_activity)
    WebView webView;
    @BindView(R.id.toolbar_noti_search)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this, this);
        webView.loadUrl(getIntent().getStringExtra("URL"));
        configureToolBar();
        onConfigureWebView();
        onPageFinished();
    }

      //-------------------//
     // Configure Toolbar //
    //-------------------//
    private void configureToolBar() {
        this.mToolbar = findViewById(R.id.toolbar_noti_search);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

      //-------------------//
     // Configure WebView //
    //-------------------//
    @SuppressLint("SetJavaScriptEnabled")
    private void onConfigureWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
    }

    private void onPageFinished() {
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}


