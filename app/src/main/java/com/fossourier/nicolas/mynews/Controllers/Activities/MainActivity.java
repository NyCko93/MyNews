package com.fossourier.nicolas.mynews.Controllers.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.fossourier.nicolas.mynews.Controllers.Fragments.MainFragment;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Views.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<MainFragment> mainFragments = new ArrayList<>();
        mainFragments.add(MainFragment.newInstance(0) );
        mainFragments.add(MainFragment.newInstance(1) );
        mainFragments.add(MainFragment.newInstance(2) );
        PagerAdapter pagerAdapter = new PagerAdapter(this, mainFragments, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(pagerAdapter);
    }
}