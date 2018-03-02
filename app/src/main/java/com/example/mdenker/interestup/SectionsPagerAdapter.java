package com.example.mdenker.interestup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    String leftOption;
    String rightOption;

    public SectionsPagerAdapter(FragmentManager fm, String leftOption, String rightOption) {
        super(fm);
        this.leftOption = leftOption;
        this.rightOption = rightOption;
    }

    @Override
    public Fragment getItem(int position) {
        //Returning the current tab
        switch(position) {
            case 0:
                CreateEventPage_GeneralTab GeneralTab = new CreateEventPage_GeneralTab();
                return GeneralTab;
            case 1:
                CreateEventPage_AdvancedTab AdvancedTab = new CreateEventPage_AdvancedTab();
                return AdvancedTab;
            default:
                System.out.println("null returned.");
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return leftOption;
            case 1:
                return rightOption;
        }
        return null;
    }
}