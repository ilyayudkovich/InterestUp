package com.example.mdenker.interestup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Home extends AppCompatActivity implements EventsListener {
    private EventAdapter adapter;
    private static boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showActionBar();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new EventAdapter(this, tabs);
        tabs.addOnTabSelectedListener(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int allItems = layoutManager.getItemCount();
                    int currentIndex = layoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if (allItems - currentIndex <= 10) {
                            new GetEventsTask().execute();
                        }
                    }
                }
            }
        });

        Database.addEventListener(this);

        new GetEventsTask().execute();
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
        //Toast.makeText(this, "Selected Interested", Toast.LENGTH_SHORT).show();
        int id = (int) ((View) view.getParent().getParent().getParent()).getTag();
        Event e = Database.getEvent(id);
        ImageButton b = view.findViewById(R.id.home_interest_button);
        if (e.getInterested().contains(User.getFullName())) {
            e.removeInterested(User.getFullName());
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_36dp));
        } else {
            e.addInterested(User.getFullName());
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_36dp));
        }
    }

    public void onGoingClick(View view) {
        //Toast.makeText(this, "Selected Going", Toast.LENGTH_SHORT).show();
        int id = (int) ((View) view.getParent().getParent().getParent()).getTag();
        Event e = Database.getEvent(id);
        ImageButton b = view.findViewById(R.id.home_going_button);
        if (e.getGoing().contains(User.getFullName())) {
            e.removeGoing(User.getFullName());
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green_36dp));
        } else {
            e.addGoing(User.getFullName());
            b.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_36dp));
        }
    }

    @Override
    public void onEventsAdded(List<Event> events) {
        adapter.addEvents(events);
    }

    @Override
    public void onEventChanged(Event event) {
        adapter.onEventChanged(event);
    }

    private static class GetEventsTask extends AsyncTask<Void, Void, List<Event>> {
        private static Calendar start = Calendar.getInstance();
        private static long duration = 604800000;

        @Override
        protected List<Event> doInBackground(Void... voids) {
            loading = true;
            return Database.fetchEvents(start.getTime(), duration);
        }

        @Override
        protected void onPostExecute(List<Event> events) {
            loading = false;
            start.setTimeInMillis(start.getTimeInMillis() + duration);
            Database.notifyEventsAdded(events);
        }
    }
}
