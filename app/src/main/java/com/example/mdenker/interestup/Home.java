package com.example.mdenker.interestup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Home extends AppCompatActivity {
    private List<Event> events = Arrays.asList(
            EventFactory.create().setName("Hiking Trip")
                    .setHost("Danielle Keller")
                    .setInterests("Hiking", "Outdoors")
                    .setLocation("Blue Mountains")
                    .setGoing("John Duke", "Liza McCall", "Rob Holt").build(),
            EventFactory.create().setName("Walk the Moon")
                    .setHost("Annie Gomez")
                    .setInterests("Concerts", "Music")
                    .setLocation("Blue Hills Bank Pavilion")
                    .setGoing("Joan Chen", "Doug Nichols", "Maria Yano")
                    .setInterested("Hayden Carter").build(),
            EventFactory.create().setName("Weekend Camping")
                    .setHost("Sam Tanner")
                    .setInterests("Outdoors", "Camping")
                    .setLocation("Red Hills")
                    .setGoing("Madison Conti").build(),
            EventFactory.create().setName("Cats the Musical")
                    .setHost("Angelo Fiorello")
                    .setInterests("Theatre", "Music")
                    .setInterested("Sarah Fowler")
                    .setLocation("Boston Opera House").build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        Set<String> tabNames = new LinkedHashSet<>(Arrays.asList("All", "Going", "Interested"));
        for (Event e : events) {
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
        EventAdapter adapter = new EventAdapter(this, this.events);
        tabs.addOnTabSelectedListener(adapter);
        recyclerView.setAdapter(adapter);
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

    public void onEventClick(View view) {
        Intent i = new Intent(this, EventDisplay.class);
        i.putExtra("event", (Event) view.getTag());
        startActivity(i);
    }
}
