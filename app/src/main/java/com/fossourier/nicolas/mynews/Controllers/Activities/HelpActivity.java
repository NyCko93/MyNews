package com.fossourier.nicolas.mynews.Controllers.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;

import com.fossourier.nicolas.mynews.R;

import java.util.Properties;

import butterknife.BindView;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        configureToolBar();
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

    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        super.onBackPressed();
    }
}
