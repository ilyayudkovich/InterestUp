package com.example.mdenker.interestup;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class GetEventsByCategoryTask extends AsyncTask<String, Void, List<Event>> {
    @Override
    protected List<Event> doInBackground(String... categories) {
        if (categories.length == 0) {
            return new ArrayList<>();
        }
        return Database.fetchEventsByCategory(categories[0]);
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        Database.notifyEventsAdded(events);
    }
}
