package com.example.mdenker.interestup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showActionBar();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        Set<String> tabNames = new LinkedHashSet<>(Arrays.asList("All", "Going", "Interested"));
        for (Event e : Database.events) {
            tabNames.addAll(e.getInterests());
        }
        for (String tabName : tabNames) {
            TabLayout.Tab t = tabs.newTab();
            t.setText(tabName);
            tabs.addTab(t);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter (see also next example)
        EventAdapter adapter = new EventAdapter(this, Database.events);
        tabs.addOnTabSelectedListener(adapter);
        recyclerView.setAdapter(adapter);

        tabs.getTabAt(1).select();
        tabs.getTabAt(0).select();
    }

    private void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled (false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(R.layout.home_action_bar);
    }

    public void onMenuItemClick(View view) {
        // Handle presses on the action bar items
        switch (view.getId()) {
            case R.id.home_page_profile:
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.home_page_search:
                startActivity(new Intent(this, Search.class));
                break;
            case R.id.home_page_new:
                startActivity(new Intent(this, CreateEventPage.class));
                break;
        }
    }

    public void onEventClick(View view) {
        Intent i = new Intent(this, EventDisplay.class);
        i.putExtra("event", (int) view.getTag());
        startActivity(i);
    }

    public void onInterestedClick(View view) {
        int id = (int) ((View) view.getParent().getParent()).getTag();
        Event e = Database.getEvent(id);
        ImageButton b = view.findViewById(R.id.home_interest_button);
        if (e.getInterested().contains(Database.user)) {
            e.removeInterested(Database.user);
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_36dp));
        } else {
            e.addInterested(Database.user);
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_36dp));
        }
    }

    public void onGoingClick(View view) {
        int id = (int) ((View) view.getParent().getParent()).getTag();
        Event e = Database.getEvent(id);
        ImageButton b = view.findViewById(R.id.home_going_button);
        if (e.getGoing().contains(Database.user)) {
            e.removeGoing(Database.user);
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green_36dp));
        } else {
            e.addGoing(Database.user);
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_36dp));
        }
    }
}
