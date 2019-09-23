package com.fossourier.nicolas.mynews.Views;


import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fossourier.nicolas.mynews.Controllers.Fragments.MainFragment;
import com.fossourier.nicolas.mynews.Utils.SharedPreferences;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<MainFragment> mMainFragments;

    private SharedPreferences mSharedPreferences;

    public PagerAdapter(Context context, ArrayList<MainFragment> mainFragments, FragmentManager fm) {
        super(fm);
        mMainFragments = mainFragments;
        mSharedPreferences = SharedPreferences.getInstance(context);
    }


    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mMainFragments.get(position);
            case 1:
                return mMainFragments.get(position);
            case 2:
                return mMainFragments.get(position);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: // Fragment 1
                return "Top Stories";
            case 1: // Fragment 2
                return "Most Popular";
            case 2: // Fragment 3
                ArrayList<String> listSection = mSharedPreferences.getListSection(0);
                if (listSection != null && listSection.size() > 0) {
                    return listSection.get(0);
                } else {
                    return "No section choisen";
                }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}