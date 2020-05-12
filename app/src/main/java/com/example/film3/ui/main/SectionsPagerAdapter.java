package com.example.film3.ui.main;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.film3.R;
import com.example.film3.listmovies.ListFilmFragment;
import com.example.film3.listtvshow.ListAcaraTvFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_movies, R.string.tab_tv_shows};
    private final Context nContext;
    private ListFilmFragment moviesFragment;
    private ListAcaraTvFragment tvShowFragment;



    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        nContext = context;
        moviesFragment = new ListFilmFragment();
        tvShowFragment = new ListAcaraTvFragment();
    }

    public Fragment getItem (int Position) {

        if (Position == 0) {
            return moviesFragment;
        }else{
            return tvShowFragment;
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        return nContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
