package com.example.filipe.piggybank;

import android.annotation.TargetApi;

import android.os.Build;

import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;


import com.example.filipe.piggybank.Fragments.CoinsFragment;
import com.example.filipe.piggybank.Fragments.NotesFragment;

import com.example.filipe.piggybank.Fragments.StatsFragment;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //joda-time initialization
//        JodaTimeAndroid.init(this);
        Log.d(TAG,"onCreate: Starting.");
       mSectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (shouldAskPermissions()) {
            askPermissions();
        }







    }

    public void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CoinsFragment(),"coins");
        adapter.addFragment(new NotesFragment(),"Notes");
        adapter.addFragment(new StatsFragment(),"Stats");
        viewPager.setAdapter(adapter);
    }




    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }



}
