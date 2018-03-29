package com.example.mdenker.interestup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    ImageButton cancelTagEditButton;
    ImageButton doneTagEditButton;
    TextView addTagButton;

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


        //Inflating different view to grab correct buttons
        View generalTabView = getLayoutInflater().inflate(R.layout.create_event_general_tab, null);
        final View advancedTabView = getLayoutInflater().inflate(R.layout.create_event_advanced_tab, null);

        //Initializing buttons
        editTagButton = (ImageButton) generalTabView.findViewById(R.id.edit_tag_button);
        cancelTagEditButton = (ImageButton) generalTabView.findViewById(R.id.cancel_edit_button);
        doneTagEditButton = (ImageButton) generalTabView.findViewById(R.id.done_edit_button);
        addTagButton = (TextView) generalTabView.findViewById(R.id.add_tag_button);

        //Grab entries
        Button createEventButton = (Button) findViewById(R.id.create_event_button);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Grab all fields from view
                EditText nameField = (EditText) findViewById(R.id.event_name_field);
                String nameFieldText = nameField.getText().toString().trim();

                EditText descriptionField = (EditText) findViewById(R.id.description_field);
                String descriptionFieldText = descriptionField.getText().toString().trim();

                EditText startDateField = (EditText) findViewById(R.id.start_date_field);
                String startDateFieldDate = (String) startDateField.getText().toString().trim();

                EditText endDateField = (EditText) findViewById(R.id.end_date_field);
                String endDateFieldDate = (String) endDateField.getText().toString().trim();

                EditText startTimeField = (EditText) findViewById(R.id.start_time_field);
                String startTimeFieldTime = (String) startTimeField.getText().toString().trim();

                EditText endTimeField = (EditText) findViewById(R.id.end_time_field);
                String endTimeFieldTime = (String) endTimeField.getText().toString().trim();

                Switch tentativeDatesSwitch = (Switch) findViewById(R.id.tentative_dates_toggle);
                Boolean tentativeDatesSwitchValue = tentativeDatesSwitch.isChecked();

                EditText locationField = (EditText) findViewById(R.id.location_field);
                String locationFieldText = locationField.getText().toString().trim();

                Spinner numberOfAttendeesSpinner = (Spinner) findViewById(R.id.number_of_attendees_field);
                int numberOfAttendees = 30;
                try {
                    numberOfAttendees = Integer.parseInt(numberOfAttendeesSpinner.getSelectedItem().toString());
                } catch (Exception e) {}

                Spinner viewRestrictionSpinner = (Spinner) findViewById(R.id._view_restriction_field);
                String viewRestrictionText = viewRestrictionSpinner.getSelectedItem().toString();

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
                    } else if (startDateFormat.after(endDateFormat) || startDateFieldDate == null || startDateFieldDate.equals("") || endDateFieldDate == null || endDateFieldDate.equals("")) {
                        Toast.makeText(getApplicationContext(), "Description field is mandatory!", Toast.LENGTH_SHORT).show();
                        errorHandler = 1;
                    } else if (startTimeFormat.after(endTimeFormat) || startTimeFieldTime == null || startTimeFieldTime.equals("") || endTimeFieldTime == null || endTimeFieldTime.equals("")) {
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


                    Calendar startDateCalendar = null;
                    Calendar endDateCalendar = null;

                    try {
                        startDateCalendar = combineDateAndTme(startDateFieldDate, startTimeFieldTime);
                        endDateCalendar = combineDateAndTme(endDateFieldDate, endTimeFieldTime);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    
                    String[] tagsToAdd = CreateEventPage_GeneralTab.tags.toArray(new String[CreateEventPage_GeneralTab.tags.size()]);

                    Event event = EventFactory.create().setName(nameFieldText).setDescription(descriptionFieldText).setStartDateTime(startDateCalendar)
                            .setEndDateTime(endDateCalendar).setTentativeDates(tentativeDatesSwitchValue).setLocation(locationFieldText)
                            .setTags(tagsToAdd).setNumberOfAttendees(numberOfAttendees).setViewRestrictions(viewRestrictionText).setExclusions(CreateEventPage_AdvancedTab.exclusions).build();
                    Database.addEvent(event);
                    OnBackClick(view);
                }
            }


        });
    }


    public Calendar combineDateAndTme(String Date, String Time) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Date DateFormat = null;
        Date TimeFormat = null;

        try {
            DateFormat = new SimpleDateFormat("MM/dd/yyyy").parse(Date);
            TimeFormat = new SimpleDateFormat("hh:mm").parse(Time);

            Date combinedDateAndTime = new Date(DateFormat.getYear(), DateFormat.getMonth(), DateFormat.getDay(), TimeFormat.getHours(), TimeFormat.getMinutes());
            calendar.setTime(combinedDateAndTime);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return calendar;
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

    public void OnScreenTapped(View view) { ForceExitKeyboard(view); }

    public void OnTagEditClick(View view) {

        enabled = !enabled;

        if (!enabled) {
            editTagButton.setVisibility(View.GONE);
            addTagButton.setVisibility(View.GONE);

            doneTagEditButton.setVisibility(View.VISIBLE);
            cancelTagEditButton.setVisibility(View.VISIBLE);

        } else {
            editTagButton.setVisibility(View.VISIBLE);
            addTagButton.setVisibility(View.VISIBLE);

            doneTagEditButton.setVisibility(View.GONE);
            cancelTagEditButton.setVisibility(View.GONE);

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