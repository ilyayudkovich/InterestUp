package com.example.mdenker.interestup;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Database {
    public static List<Event> events = new ArrayList<>();
    private static List<EventsListener> listeners = new ArrayList<>();

    public static void addEventListener(EventsListener listener) {
        listeners.add(listener);
    }

    public static void notifyEventsAdded(List<Event> events) {
        for (EventsListener listener : listeners) {
            listener.onEventsAdded(events);
        }
    }

    public static void notifyEventChanged(Event event) {
        for (EventsListener listener : listeners) {
            listener.onEventChanged(event);
        }
    }

    public static Event getEvent(int id) {
        for (Event e : events) {
            if (e.getID() == id) {
                return e;
            }
        }
        return null;
    }

    public static void addEvent(Event e) {
        events.add(e);
        for (EventsListener listener : listeners) {
            listener.onEventsAdded(Collections.singletonList(e));
        }
    }

    public static List<Event> fetchEvents(Date startRange, long duration) {
        String spec = "https://api.meetup.com/find/upcoming_events?" +
                "key=7e6810756824521576265de5f124652" +
                "&order=time" +
                "&fields=group_category,+event_hosts,+plain_text_description";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss", Locale.US);
        String formStartRange = format.format(startRange);
        spec += "&start_date_range=" + formStartRange;

        startRange.setTime(startRange.getTime() + duration);
        String formEndRange = format.format(startRange);
        spec += "&end_date_range=" + formEndRange;

        List<Event> added = new ArrayList<>();
        try {
            URL url = new URL(spec);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            MeetupEvents meetupEvents = new MeetupEvents();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                meetupEvents = new Gson().fromJson(reader, MeetupEvents.class);
                reader.close();
            }

            for (MeetupEvents.MeetupEvent event : meetupEvents.getEvents()) {
                String startString = event.getLocalDate() + " " + event.getLocalTime();
                Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).parse(startString);
                Date endDate = new Date(startDate.getTime() + event.getDuration());
                Calendar start = Calendar.getInstance();
                start.setTime(startDate);
                Calendar end = Calendar.getInstance();
                end.setTime(endDate);
                Event e = EventFactory.create()
                        .setName(event.getName())
                        .setDescription(event.getDescription())
                        .setStartDateTime(start)
                        .setEndDateTime(end)
                        .setLocation(event.getVenue())
                        .setHost(event.getHost())
                        .setTags(event.getCategory())
                        .build();
                added.add(e);
                events.add(e);
            }
        } catch (IOException |ParseException e) {
            e.printStackTrace();
        }
        return added;
    }
}
