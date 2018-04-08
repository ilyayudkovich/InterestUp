package com.example.mdenker.interestup;

import android.os.AsyncTask;

import java.util.Calendar;
import java.util.List;

public class GetEventsByDateTask extends AsyncTask<Void, Void, List<Event>> {
    private static boolean loading = false;
    private static Calendar start = Calendar.getInstance();
    private static long duration = 604800000;

    @Override
    protected List<Event> doInBackground(Void... voids) {
        loading = true;
        return Database.fetchEventsByDate(start.getTime(), duration);
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        loading = false;
        start.setTimeInMillis(start.getTimeInMillis() + duration);
        Database.notifyEventsAdded(events);
    }

    public static boolean isLoading() {
        return loading;
    }
}
