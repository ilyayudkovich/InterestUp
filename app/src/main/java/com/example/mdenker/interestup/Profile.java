package com.example.mdenker.interestup;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


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

    public boolean getEnabled() {return enabled;}

    public static boolean canceled = false;  // this is easier than making custom listener

    TextView userName;
    TextView userLocation;

    ImageButton backButton;
    Button editButton;
    Button cancelButton;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        User user = new User(); // initializes the user

        mSectionsPagerAdapter = new ProfileTabAdapter(getSupportFragmentManager(), "INTERESTS", "PERSONAL INFO");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.ProfileContainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        userName = (TextView) findViewById(R.id.profileName);
        userLocation = (TextView) findViewById(R.id.profileLocation);

        userName.setText(User.getFullName());
        userLocation.setText(User.getLocation());

        backButton = (ImageButton) findViewById(R.id.backButton);
        editButton = (Button) findViewById(R.id.editButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        doneButton = (Button) findViewById(R.id.doneButton);

        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OnEditClick(v);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                canceled = true;
                OnEditClick(v);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               OnEditClick(v);
            }
        });

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
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void EnableDisableView(View view) {

        // so that only fields that are touchable (like text fields) are focusable ever
        if (view.getTag() != null && view.getTag().toString().equals("touchableFields")) {
            if (!enabled && view.isFocused()) {
                ForceExitKeyboard(view);
                view.clearFocus(); // makes sure you're not still focused no the item after removing keyboard
            }
            view.setFocusableInTouchMode(enabled);
        }

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;

            for (int idx = 0; idx < group.getChildCount(); idx++) {
                EnableDisableView(group.getChildAt(idx));
            }
        }
    }

    public void OnScreenTapped(View view) {
        ForceExitKeyboard(view);
    }


    public void OnEditClick(View view) {
        enabled = !enabled;

        if (!enabled) {
            doneButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);

            editButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);

            if (canceled) {
                userName.setText(User.getFullName());
                userLocation.setText(User.getLocation());
            }
            else {
                User.setFullName(userName.getText().toString());
                User.setLocation(userLocation.getText().toString());
            }
        }
        else {
            doneButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);

            editButton.setVisibility(View.GONE);
            backButton.setVisibility(View.GONE);
        }
        EnableDisableView(mViewPager);
        EnableDisableView(findViewById(R.id.profileTopInfo));

        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + mViewPager.getId() + ":" + mViewPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (mViewPager.getCurrentItem() == 0 && page != null) {
            ((Profile_InterestsTab)page).toggledEdit(enabled);
        }
        else if (mViewPager.getCurrentItem() == 1 && page != null) {
            ((Profile_PersonalInfoTab)page).toggledEdit(enabled);
        }
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
