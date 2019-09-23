package com.fossourier.nicolas.mynews.Views;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fossourier.nicolas.mynews.Controllers.Fragments.MainFragment;
import com.fossourier.nicolas.mynews.R;
import com.fossourier.nicolas.mynews.Utils.SharedPreferences;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PagerAdapter extends FragmentPagerAdapter {


//    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    private final ArrayList<MainFragment> mMainFragments;

    private static SharedPreferences mSharedPreferences;

    public PagerAdapter(Context context, ArrayList<MainFragment> mainFragments, FragmentManager fm) {
        super(fm);
        mMainFragments = mainFragments;
        mContext = context;
        mSharedPreferences = SharedPreferences.getInstance(context);
    }

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
                    return "Business";
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