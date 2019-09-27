package com.fossourier.nicolas.mynews.Controllers.Activities;
import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.fossourier.nicolas.mynews.R;
import butterknife.BindView;
import butterknife.ButterKnife;

// Webview for display the article clicked
public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view_activity)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this, this);
        webView.loadUrl(getIntent().getStringExtra("URL"));
    }
}


