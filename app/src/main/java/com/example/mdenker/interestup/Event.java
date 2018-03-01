package com.example.mdenker.interestup;

import android.text.format.DateFormat;
import android.text.format.Time;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by tsengjonathan on 2/28/18.
 */

public class Event {
    int id;
    String name;
    String description;
    DateFormat startDate;
    DateFormat endDate;
    Time startTime;
    Time endTime;
    boolean tentativeDates;
    String location;
    ArrayList tags;
    int numberOfAttendees;
    String viewRestrictions;
    ArrayList exclusions;


    Event(String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.tentativeDates = tentativeDates;
        this.location = location;
        this.tags = tags;
        this.numberOfAttendees = numberOfAttendees;
        this.viewRestrictions = viewRestrictions;
        this.exclusions = exclusions;
    }
    public int getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public DateFormat getStartDate() {
        return this.startDate;
    }
    public DateFormat getEndDate() {return this.endDate;}
    public Time getStartTime() {return this.startTime;}
    public Time getEndTime() {return this.endTime;}
    public boolean getTentativeDates() {return this.tentativeDates;}
    public String getLocation() {return this.location;}
    public ArrayList getTags() {return this.tags;}
    public int getNumberOfAttendees() { return this.numberOfAttendees;}
    public String getViewRestrictions() {return this.viewRestrictions;}
    public ArrayList getExclusions() {return this.exclusions;}
}
