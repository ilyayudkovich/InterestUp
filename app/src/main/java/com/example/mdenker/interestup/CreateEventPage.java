package com.example.mdenker.interestup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEventPage extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private boolean enabled = true;

    ImageButton editTagButton;
    Button cancelTagEditButton;
    Button doneTagEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_page);

        //Removing toolbar
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), "GENERAL", "ADVANCED");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        editTagButton = (ImageButton) findViewById(R.id.edit_tag_button);
        cancelTagEditButton = (Button) findViewById(R.id.cancel_edit_button);
        doneTagEditButton = (Button) findViewById(R.id.done_edit_button);

        //Grab entries
        Button createEventButton = (Button) findViewById(R.id.create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Grab all mandatory fields from view
                EditText nameField = (EditText) findViewById(R.id.event_name_field);
                String nameFieldText = nameField.getText().toString().trim();

                EditText descriptionField = (EditText) findViewById(R.id.description_field);
                String descriptionFieldText = descriptionField.getText().toString().trim();

                EditText startDateField = (EditText) findViewById(R.id.start_date_field);
                String startDateFieldDate = (String) startDateField.getText().toString().trim();

                EditText endDateField = (EditText) findViewById(R.id.end_date_field);
                String endDateFieldDate = (String) endDateField.getText().toString().trim();

                EditText locationField = (EditText) findViewById(R.id.location_field);
                String locationFieldText = nameField.getText().toString().trim();

                //Convert date strings to Java Date
                @SuppressLint("SimpleDateFormat")
                Date startDateFormat = null;
                Date endDateFormat = null;
                try {
                    startDateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(startDateFieldDate);
                    endDateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(endDateFieldDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                EditText startTimeField = (EditText) findViewById(R.id.start_time_field);
                String startTimeFieldTime = (String) startTimeField.getText().toString().trim();

                EditText endTimeField = (EditText) findViewById(R.id.end_time_field);
                String endTimeFieldTime = (String) endTimeField.getText().toString().trim();

                //Convert time strings to Java Time
                Date startTimeFormat = null;
                Date endTimeFormat = null;
                try {
                    startTimeFormat = new SimpleDateFormat("hh:mm").parse(startTimeFieldTime);
                    endTimeFormat = new SimpleDateFormat("hh:mm").parse(endTimeFieldTime);


                } catch(Exception e) {
                    e.printStackTrace();
                }


                //Checks if there is an error on the page, if there is, won't let you create event.
                int errorHandler = 0;

                //Check if mandatory fields are empty or invalid
                try {
                    if (nameFieldText.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Name field is mandatory!", Toast.LENGTH_SHORT).show();
                        errorHandler = 1;
                    } else if (descriptionFieldText.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Description field is mandatory!", Toast.LENGTH_SHORT).show();
                        errorHandler = 1;
                    } else if (startDateFormat.after(endDateFormat) || startDateFieldDate == null || startDateFieldDate.equals("") || endDateFieldDate == null || endDateFieldDate.equals("")) {//TODO do date validation
                        Toast.makeText(getApplicationContext(), "Description field is mandatory!", Toast.LENGTH_SHORT).show();
                        System.out.println("Date fields must be valid!");
                        errorHandler = 1;
                    } else if (startTimeFormat.after(endTimeFormat) || startTimeFieldTime == null || startTimeFieldTime.equals("") || endTimeFieldTime == null || endTimeFieldTime.equals("")) {//TODO do time validation
                        Toast.makeText(getApplicationContext(), "Time fields must be valid!", Toast.LENGTH_SHORT).show();
                        errorHandler = 1;
                    } else if (locationFieldText.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Location field is mandatory!", Toast.LENGTH_SHORT).show();
                        errorHandler = 1;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

                if(errorHandler == 0) {
                    Toast.makeText(getApplicationContext(), "Event created!", Toast.LENGTH_SHORT).show();
                    System.out.println("Do something here to create event.");
                    OnBackClick(view);
                }
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Functionality for back button
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

    public void OnTagEditClick(View view) {
        enabled = !enabled;

        if (!enabled) {
            doneTagEditButton.setVisibility(View.GONE);
            cancelTagEditButton.setVisibility(View.GONE);
            editTagButton.setVisibility(View.VISIBLE);
        }
        else {
            doneTagEditButton.setVisibility(View.VISIBLE);
            cancelTagEditButton.setVisibility(View.VISIBLE);
            editTagButton.setVisibility(View.GONE);
        }
        EnableDisableView(mViewPager);
    }

    public class CreateEventTabAdapter extends SectionsPagerAdapter {

        public CreateEventTabAdapter(FragmentManager fm, String leftOption, String rightOption) {
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
                    CreateEventPage_AdvancedTab AdvancedTab = new CreateEventPage_AdvancedTab();
                    return AdvancedTab;
                default:
                    return null;
            }
        }
    }

    //Deleted PlaceholderFragment class from here since each tab has it's own class now.
}