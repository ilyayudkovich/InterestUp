package com.example.mdenker.interestup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Collections;

public class Home extends AppCompatActivity {

    private String[] tabNames = {"All", "Interested", "Going", "Concerts",
            "Food + Drink", "Outdoors", "Sports", "Gaming", "Comedy", "Art"};
    private Event[] events = {new Event("Hiking Trip", "2018/03/21", "Blue Mountains", Collections.<String>emptyList()),
            new Event("Go Hiking", "2018/04/01", "Red Hills", Collections.<String>emptyList()),
            new Event("Cats the Musical", "2018/06/17", "Boston Opera House", Collections.<String>emptyList())};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
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
        EventAdapter adapter = new EventAdapter(this.events);
        recyclerView.setAdapter(adapter);

        //add back button
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_page, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.home_page_profile:
                startActivity(new Intent(this, Profile.class));
                return true;
            case R.id.home_page_search:
                startActivity(new Intent(this, Search.class));
                return true;
            case R.id.home_page_new:
                startActivity(new Intent(this, CreateEventPage.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
