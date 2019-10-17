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

    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.web_view_activity);
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
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        assert ab != null;
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

    public void onBackPressed() {
        if(webView!= null && webView.canGoBack())
            webView.goBack();// if there is previous page open it
        else
            super.onBackPressed();//if there is no previous page, close app
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}


