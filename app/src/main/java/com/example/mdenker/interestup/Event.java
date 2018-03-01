package com.example.mdenker.interestup;

import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by tsengjonathan on 2/28/18.
 */

public class Event {
    private int id;
    private String name;
    private String description;
    private DateFormat startDate;
    private DateFormat endDate;
    private Time startTime;
    private Time endTime;
    private boolean tentativeDates;
    private String location;
    private Collection<String> interests;
    private int numberOfAttendees;
    private String viewRestrictions;
    private ArrayList exclusions;

    Event(String name, String date, String location, Collection<String> interests) {
        //this.id = id;
        this.name = name;
        //this.description = description;
        //this.startDate = startDate;
        //this.endDate = endDate;
        //this.startTime = startTime;
        //this.endTime = endTime;
        //this.tentativeDates = tentativeDates;
        this.location = location;
        this.interests = interests;
        //this.numberOfAttendees = numberOfAttendees;
        //this.viewRestrictions = viewRestrictions;
        //this.exclusions = exclusions;
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

    public DateFormat getEndDate() {
        return this.endDate;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public boolean getTentativeDates() {
        return this.tentativeDates;
    }

    public String getLocation() {
        return this.location;
    }

    public Collection<String> getInterests() {
        return this.interests;
    }

    public int getNumberOfAttendees() {
        return this.numberOfAttendees;
    }

    public String getViewRestrictions() {
        return this.viewRestrictions;
    }

    public ArrayList getExclusions() {
        return this.exclusions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(DateFormat startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(DateFormat endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setTentativeDates(boolean tentativeDates) {
        this.tentativeDates = tentativeDates;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setInterests(Collection<String> interests) {
        this.interests = interests;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public void setViewRestrictions(String viewRestrictions) {
        this.viewRestrictions = viewRestrictions;
    }

    public void setExclusions(ArrayList exclusions) {
        this.exclusions = exclusions;
    }
}
