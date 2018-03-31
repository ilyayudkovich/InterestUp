package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class EventDisplay extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private com.example.mdenker.interestup.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_display);
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new CreateEventTabAdapter(getSupportFragmentManager(), "INFO", "DISCUSSION");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.event_display_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.event_view_tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        event = Database.getEvent(getIntent().getExtras().getInt("event"));
    }

    //Functionality for back button
    public void OnBackClick(View view) {
        Database.notifyEventChanged(this.event);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_display, menu);
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

    public void onEventInterestedClick(View view) {
        Toast.makeText(this, "Selected Interested", Toast.LENGTH_SHORT).show();
        ((EventDisplayInfo) mSectionsPagerAdapter.getItem(0)).onInterestedClick(view);
    }

    public void onEventGoingClick(View view) {
        Toast.makeText(this, "Selected Going", Toast.LENGTH_SHORT).show();
        ((EventDisplayInfo) mSectionsPagerAdapter.getItem(0)).onGoingClick(view);
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
                    return new EventDisplayInfo(EventDisplay.this, event);
                case 1:
                    return new DiscussionMessageFragment(EventDisplay.this);
                default:
                    System.out.println("null returned.");
                    return null;
            }
        }
    }

    public void onDiscussionSendClick(View view) {
        ((DiscussionMessageFragment) mSectionsPagerAdapter.getItem(1)).onClick(view);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println(keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                onDiscussionSendClick(this.mViewPager);
                return true;
            default:
                return false;
        }
    }
}
