package com.example.mdenker.interestup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by mdenker on 2/28/18.
 */

public class Profile extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
/*
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), "INTERESTS", "PERSONAL INFO");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        */
    }

    public void OnBackClick(View view) {
        this.finish();
    }

    public void OnEditClick(View view) {
        //Intent i = new Intent(this, Profile.class); // figure out this
        //startActivity(i);
    }

    public class ProfileTabAdapter extends SectionsPagerAdapter {

        public ProfileTabAdapter(FragmentManager fm, String leftOption, String rightOption) {
            super(fm, leftOption, rightOption);
        }

        @Override
        public Fragment getItem(int position) {
            //Returning the current tab
            switch(position) {
                case 0:
                    CreateEventPage_GeneralTab GeneralTab = new CreateEventPage_GeneralTab();
                    return GeneralTab;
                case 1:
                    System.out.println("hi there");
                    CreateEventPage_AdvancedTab AdvancedTab = new CreateEventPage_AdvancedTab();
                    return AdvancedTab;
                default:
                    System.out.println("null returned.");
                    return null;
            }
        }
    }

}
