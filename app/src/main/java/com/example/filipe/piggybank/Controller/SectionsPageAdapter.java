package com.example.filipe.piggybank.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 20/04/2017.
 */
/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPageAdapter extends FragmentPagerAdapter{

    public final String TAG = getClass().getName();
    private final List<Fragment> mFramentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionsPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title)
    {
        mFramentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFramentList.get(position);
    }

    @Override
    public int getCount() {
        return mFramentList.size();
    }
}
