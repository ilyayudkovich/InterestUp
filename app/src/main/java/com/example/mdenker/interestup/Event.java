package com.example.mdenker.interestup;

import java.time.LocalDate;
import java.util.GregorianCalendar;

/**
 * Created by tsengjonathan on 2/28/18.
 */

public class Event {
    String name;
    String date;
    String location;

    Event(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getLocation() {
        return this.location;
    }
}
