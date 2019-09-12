package com.fossourier.nicolas.mynews.Controllers.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fossourier.nicolas.mynews.Controllers.Fragments.MainFragment;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Views.PagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<MainFragment> mainFragments = new ArrayList<>();
        mainFragments.add(MainFragment.newInstance(0));
        mainFragments.add(MainFragment.newInstance(1));
        mainFragments.add(MainFragment.newInstance(2));
        PagerAdapter pagerAdapter = new PagerAdapter(this, mainFragments, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(pagerAdapter);

        configureToolBar();
        configureDrawerLayout();
        configureNavigationView();


    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_menu_drawer_section_arts:
                break;
            case R.id.activity_main_menu_drawer_section_automobiles:
                break;
            case R.id.activity_main_menu_drawer_section_books:
                break;
            case R.id.activity_main_menu_drawer_section_business:
                break;
            case R.id.activity_main_menu_drawer_section_fashion:
                break;
            case R.id.activity_main_menu_drawer_section_food:
                break;
            case R.id.activity_main_menu_drawer_section_health:
                break;
            case R.id.activity_main_menu_drawer_section_home:
                break;
            case R.id.activity_main_menu_drawer_section_insider:
                break;
            case R.id.activity_main_menu_drawer_section_magazine:
                break;
            case R.id.activity_main_menu_drawer_section_movies:
                break;
            case R.id.activity_main_menu_drawer_section_national:
                break;
            case R.id.activity_main_menu_drawer_section_nyregion:
                break;
            case R.id.activity_main_menu_drawer_section_obituaries:
                break;
            case R.id.activity_main_menu_drawer_section_opinion:
                break;
            case R.id.activity_main_menu_drawer_section_politics:
                break;
            case R.id.activity_main_menu_drawer_section_realestate:
                break;
            case R.id.activity_main_menu_drawer_section_science:
                break;
            case R.id.activity_main_menu_drawer_section_sports:
                break;
            case R.id.activity_main_menu_drawer_section_sundayreview:
                break;
            case R.id.activity_main_menu_drawer_section_technology:
                break;
            case R.id.activity_main_menu_drawer_section_theater:
                break;
            case R.id.activity_main_menu_drawer_section_tmagazine:
                break;
            case R.id.activity_main_menu_drawer_section_travel:
                break;
            case R.id.activity_main_menu_drawer_section_upshot:
                break;
            case R.id.activity_main_menu_drawer_section_world:
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // 3 - Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }
}