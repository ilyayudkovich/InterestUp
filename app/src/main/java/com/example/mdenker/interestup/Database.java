package com.example.mdenker.interestup;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Database {
    public static final String user = "Jordan You";

    public static List<Event> events = new ArrayList<>();

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
    }

    public static void fetchEvents() {
        try {
            URL url = new URL("https://api.meetup.com/find/upcoming_events?key=7e6810756824521576265de5f124652");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            MeetupEvents events = new MeetupEvents();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                events = new Gson().fromJson(reader, MeetupEvents.class);
                reader.close();
            }

            for (MeetupEvents.MeetupEvent event : events.getEvents()) {
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
                        .build();
                Database.addEvent(e);
            }
        } catch (IOException |ParseException e) {
            e.printStackTrace();
        }
    }
}
