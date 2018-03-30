package com.example.mdenker.interestup;

import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tsengjonathan on 2/28/18.
 */

public class Event implements Serializable {
    private int id;
    private String host;
    private String name;
    private String description;
    private Calendar startDateTime;
    private Calendar endDateTime;
    private boolean tentativeDates;
    private String location;
    private List<String> interests;
    private List<String> going;
    private List<String> interested;
    private List<String> items;
    private int numberOfAttendees;
    private String viewRestrictions;
    private List<String> exclusions;

    public Event() {
        this.id = -1;
        this.host = "";
        this.name = "";
        this.description = "";
        this.startDateTime = Calendar.getInstance();
        this.endDateTime = Calendar.getInstance();
        this.tentativeDates = true;
        this.location = "";
        this.interests = new ArrayList<>();
        this.going = new ArrayList<>();
        this.interested = new ArrayList<>();
        this.items = new ArrayList<>();
        this.numberOfAttendees = 1;
        this.viewRestrictions = "";
        this.exclusions = new ArrayList<>();
    }

    public Event(String name, String date, String location) {
        this.id = -1;
        this.host = "";
        this.name = name;
        this.description = "";
        this.startDateTime = Calendar.getInstance();
        this.endDateTime = Calendar.getInstance();
        this.tentativeDates = true;
        this.location = location;
        this.interests = new ArrayList<>();
        this.going = new ArrayList<>();
        this.interested = new ArrayList<>();
        this.items = new ArrayList<>();
        this.numberOfAttendees = 1;
        this.viewRestrictions = "";
        this.exclusions = new ArrayList<>();
    }

    public int getID() {
        return this.id;
    }

    public String getHost() {
        return this.host;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Calendar getStartDateTime() {
        return this.startDateTime;
    }

    public Calendar getEndDateTime() {
        return this.endDateTime;
    }

    public boolean getTentativeDates() {
        return this.tentativeDates;
    }

    public String getLocation() {
        return this.location;
    }

    public List<String> getInterests() {
        return this.interests;
    }

    public List<String> getGoing() {
        List<String> allGoing = new ArrayList<>();
        allGoing.add(this.host);
        allGoing.addAll(this.going);
        return allGoing;
    }

    public List<String> getInterested() {
        List<String> allInterested = new ArrayList<>();
        allInterested.add(this.host);
        allInterested.addAll(this.interested);
        return allInterested;
    }

    public List<String> getItems() {
        return this.items;
    }

    public int getNumberOfAttendees() {
        return this.numberOfAttendees;
    }

    public String getViewRestrictions() {
        return this.viewRestrictions;
    }

    public List<String> getExclusions() {
        return this.exclusions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDateTime(Calendar startDate) {
        this.startDateTime = startDate;
    }

    public void setEndDateTime(Calendar endDate) {
        this.endDateTime = endDate;
    }

    public void setTentativeDates(boolean tentativeDates) {
        this.tentativeDates = tentativeDates;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public void setViewRestrictions(String viewRestrictions) {
        this.viewRestrictions = viewRestrictions;
    }

    public void setExclusions(List<String> exclusions) {
        this.exclusions = exclusions;
    }

    public void addGoing(String going) {
        this.going.add(going);
    }

    public void addGoing(List<String> going) {
        this.going.addAll(going);
    }

    public void removeGoing(String going) {
        this.going.remove(going);
    }

    public void addInterested(String interested) {
        this.interested.add(interested);
    }

    public void addInterested(List<String> interested) {
        this.interested.addAll(interested);
    }

    public void removeInterested(String interested) {
        this.interested.remove(interested);
    }
}
