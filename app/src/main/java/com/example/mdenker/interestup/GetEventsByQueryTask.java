package com.example.mdenker.interestup;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class GetEventsByQueryTask extends AsyncTask<String, Void, List<Event>> {
    @Override
    protected List<Event> doInBackground(String... queries) {
        if (queries.length == 0) {
            return new ArrayList<>();
        }
        return Database.fetchEventsByQuery(queries[0]);
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        Database.notifyEventsAdded(events);
    }
}
