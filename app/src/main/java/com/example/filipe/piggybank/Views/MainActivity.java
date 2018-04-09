package com.example.filipe.piggybank.Views;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.filipe.piggybank.Controller.SectionsPageAdapter;
import com.example.filipe.piggybank.DB.DatabaseHelper;
import com.example.filipe.piggybank.R;
import com.example.filipe.piggybank.Services.FileUtils;

import net.danlew.android.joda.JodaTimeAndroid;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPagerAdapter;
    private ViewPager viewPager;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dá erro se não for comentado
//        if(savedInstanceState == null)
//        {
//            getSupportFragmentManager().beginTransaction()
//                    .add(new Fragment(),TAG)
//                    .commit();
//        }
        //joda-time initialization
        JodaTimeAndroid.init(this);
        Log.d(TAG,"onCreate: Starting App...");
        mSectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        databaseHelper = new DatabaseHelper(this);
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT);
    }

    /**
     * Setup the fragment views to be created and it's names
     * @param viewPager
     */
    public void setupViewPager(ViewPager viewPager)
    {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TotalFragment(),"Total");
        adapter.addFragment(new CoinsFragment(),"coins");
        adapter.addFragment(new StatsFragment(),"Statistics");
        viewPager.setAdapter(adapter);
    }


    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_INTERNAL_STORAGE",
                "android.permission.WRITE_INTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }
}
