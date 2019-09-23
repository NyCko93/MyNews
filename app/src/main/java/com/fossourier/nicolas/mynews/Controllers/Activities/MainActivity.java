package com.fossourier.nicolas.mynews.Controllers.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fossourier.nicolas.mynews.Controllers.Fragments.MainFragment;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Utils.SharedPreferences;
import com.fossourier.nicolas.mynews.Views.PagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private TabLayout tabs;
    private PagerAdapter pagerAdapter;

    private static SharedPreferences mSharedPreferences;

    // ID of fragment for drawer
    public static final int TOP_STORIES = 0;
    public static final int  MOST_POPULAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<MainFragment> mainFragments = new ArrayList<>();
        mainFragments.add(MainFragment.newInstance(0));
        mainFragments.add(MainFragment.newInstance(1));
        mainFragments.add(MainFragment.newInstance(2));
        pagerAdapter = new PagerAdapter(this, mainFragments, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(pagerAdapter);

        configureToolBar();
        configureDrawerLayout();
        configureNavigationView();

        mSharedPreferences = SharedPreferences.getInstance(this);


    }

    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    // Item of my section's menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle Navigation Item Click
        int id = item.getItemId();
        item.getTitle();
        String sectionChoisen;

        switch (id) {
            case R.id.category_top_stories:
                this.displayFragment(TOP_STORIES);
            break;
            case R.id.category_most_popular:
                this.displayFragment(MOST_POPULAR);
                break;
            case R.id.activity_main_menu_drawer_section_arts:
                sectionChoisen = getString(R.string.section_arts);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_automobiles:
                sectionChoisen = getString(R.string.section_automobiles);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_books:
                sectionChoisen = getString(R.string.section_books);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_business:
                sectionChoisen = getString(R.string.section_business);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_fashion:
                sectionChoisen = getString(R.string.section_fashion);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_food:
                sectionChoisen = getString(R.string.section_food);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_health:
                sectionChoisen = getString(R.string.section_health);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_home:
                sectionChoisen = getString(R.string.section_home);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_insider:
                sectionChoisen = getString(R.string.section_insider);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_magazine:
                sectionChoisen = getString(R.string.section_magazine);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_movies:
                sectionChoisen = getString(R.string.section_movies);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_national:
                sectionChoisen = getString(R.string.section_national);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_nyregion:
                sectionChoisen = getString(R.string.section_nyregion);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_obituaries:
                sectionChoisen = getString(R.string.section_obituaries);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_opinion:
                sectionChoisen = getString(R.string.section_opinion);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_politics:
                sectionChoisen = getString(R.string.section_politics);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_realestate:
                sectionChoisen = getString(R.string.section_realestate);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_science:
                sectionChoisen = getString(R.string.section_science);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_sports:
                sectionChoisen = getString(R.string.section_sports);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_sundayreview:
                sectionChoisen = getString(R.string.section_sundayreview);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_technology:
                sectionChoisen = getString(R.string.section_technology);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_theater:
                sectionChoisen = getString(R.string.section_theater);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_tmagazine:
                sectionChoisen = getString(R.string.section_tmagazine);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_travel:
                sectionChoisen = getString(R.string.section_travel);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_upshot:
                sectionChoisen = getString(R.string.section_upshot);
                displaySectionChoisen(sectionChoisen);
                break;
            case R.id.activity_main_menu_drawer_section_world:
                sectionChoisen = getString(R.string.section_world);
                displaySectionChoisen(sectionChoisen);
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // Display the section choisen in fragment and refresh title with the name of section choisen
    private void displaySectionChoisen(String sectionChoisen){
        viewPager.setCurrentItem(2);
        Objects.requireNonNull(tabs.getTabAt(2)).setText(sectionChoisen);
        ((MainFragment) pagerAdapter.getItem(2)).updateFragmentSection(sectionChoisen);

        // Here we save the section choisen in class sharedPreferences
        ArrayList<String> listSection = mSharedPreferences.getListSection(0);
        if (listSection.size() > 0) {
            listSection.remove(0);
        }
        listSection.add(sectionChoisen);
        mSharedPreferences.storeListSection(0, listSection);
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // Configure Toolbar
    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

    // Item of my drawer, for display the fragment according
    private void displayFragment(int id){
        switch(id){
            case TOP_STORIES:
                viewPager.setCurrentItem(TOP_STORIES);
                navigationView.setCheckedItem(R.id.category_top_stories);
                break;
            case MOST_POPULAR:
                viewPager.setCurrentItem(MOST_POPULAR);
                navigationView.setCheckedItem(R.id.category_most_popular);
                break;
        }
    }
}