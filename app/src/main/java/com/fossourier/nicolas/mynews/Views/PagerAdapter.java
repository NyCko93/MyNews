package com.fossourier.nicolas.mynews.Views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Controllers.Fragments.MainFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MainFragment.newInstance(0).executeHttpRequestTopStories();
                Log.e("TAG", "Requete TopStories !!");
                break;
            case 1:
                MainFragment.newInstance(1).executeHttpRequestMostPopular();
                Log.e("TAG", "Requete MostPopular !!");
                break;
            case 2:
                MainFragment.newInstance(2).executeHttpRequestMovieReviews();
                Log.e("TAG", "Requete MovieReviews !!");
                break;
        }

        return MainFragment.newInstance(position);

    }




//    @Override
//    public Fragment getItem(int position) {
//        // getItem is called to instantiate the fragment for the given page.
//        // Return a MainFragment (defined as a static inner class below).
//        switch (position) {
//            case 0: MainFragment.newInstance(0).executeHttpRequestTopStories();
//                Log.e("TAG", "Requete TopStories !!");
//                break;
//            case 1: MainFragment.newInstance(1);
//                Log.e("TAG", "Requete MostPopular !!");
//                break;
//            case 2: MainFragment.newInstance(2);
//                Log.e("TAG", "Requete MovieReviews !!");
//                break;
//        }
//        return MainFragment.newInstance(position);
//    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}