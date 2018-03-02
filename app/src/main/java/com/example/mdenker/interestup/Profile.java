package com.example.mdenker.interestup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by mdenker on 2/28/18.
 */

public class Profile extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private boolean enabled = true;

    ImageButton backButton;
    ImageButton editButton;
    Button cancelButton;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        mSectionsPagerAdapter = new ProfileTabAdapter(getSupportFragmentManager(), "INTERESTS", "PERSONAL INFO");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.ProfileContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        backButton = (ImageButton) findViewById(R.id.backButton);
        editButton = (ImageButton) findViewById(R.id.editButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        doneButton = (Button) findViewById(R.id.doneButton);

 /*
        int index = 0;
        interestsLayout = findViewById(R.id.InterestContainer);
        LinearLayout interestRow = new LinearLayout(this);

        interestsLayout.addView(interestRow);

        interestRow.setOrientation(LinearLayout.HORIZONTAL);
        interestRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        topInterestRow = interestRow;

        while (index < interests.size()) {
            if (index > 0 && index % 2 == 0) {
                interestRow = new LinearLayout(this);
                interestsLayout.addView(interestRow);
                interestRow.setOrientation(LinearLayout.HORIZONTAL);
                interestRow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                topInterestRow = interestRow;
            }
            TextView interestItem = new TextView(this);
            interestItem.setText(interests.get(index));
            interestRow.addView(interestItem);
            //ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //titleView.setLayoutParams(lparams);
            //interestItem.setTextAppearance(this, android.R.attr.textAppearanceLarge); // TODO how to add text appearance in xml
            index++;
        }
*/
    }

    @Override
    protected void onStart() {
        super.onStart();

        OnEditClick(mViewPager);
    }

    public void OnBackClick(View view) {
        this.finish();
    }



    private void ForceExitKeyboard(View view) {
        //View view = this.getCurrentFocus();
        //if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
       // }
  //      view.restoreDefaultFocus();
    }

    private void EnableDisableView(View view) {

        if (view.getId() != R.id.AddInterest) {

            if (!enabled && view.isFocused()) {
                ForceExitKeyboard(view);
                view.clearFocus(); // makes sure you're not still focused no the item after removing keyboard
            }

            view.setFocusableInTouchMode(enabled);


            if (view instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) view;

                for (int idx = 0; idx < group.getChildCount(); idx++) {
                    EnableDisableView(group.getChildAt(idx));
                }
            }
        }
    }

    public void OnScreenTapped(View view) {
        ForceExitKeyboard(view);
    }


    public void OnEditClick(View view) {
        //mViewPager.setFocusable(true);
        enabled = !enabled;

        if (!enabled) {
        //    ForceExitKeyboard();
            doneButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);

            editButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
        }
        else {
            doneButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);

            editButton.setVisibility(View.GONE);
            backButton.setVisibility(View.GONE);
        }
        EnableDisableView(mViewPager);
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
                    Profile_InterestsTab firstTab = new Profile_InterestsTab();
                    return firstTab;
                case 1:
                    Profile_PersonalInfoTab secondTab = new Profile_PersonalInfoTab();
                    return secondTab;
                default:
                    System.out.println("null returned.");
                    return null;
            }
        }
    }

}
