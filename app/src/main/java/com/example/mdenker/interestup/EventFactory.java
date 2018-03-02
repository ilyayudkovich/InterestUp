package com.example.mdenker.interestup;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by micha on 3/2/2018.
 */

public class EventFactory {
    private static EventFactory factory;
    private static Event event;

    private EventFactory() {
        event = new Event();
    }

    public static EventFactory create() {
        factory = new EventFactory();
        return factory;
    }

    public EventFactory setHost(String host) {
        event.setHost(host);
        return factory;
    }

    public EventFactory setName(String name) {
        event.setName(name);
        return factory;
    }

    public EventFactory setDescription(String description) {
        event.setDescription(description);
        return factory;
    }

    public EventFactory setStartDateTime(Calendar startDate) {
        event.setStartDateTime(startDate);
        return factory;
    }

    public EventFactory setEndDateTime(Calendar endDate) {
        event.setEndDateTime(endDate);
        return factory;
    }

    public EventFactory setTentativeDates(boolean tentativeDates) {
        event.setTentativeDates(tentativeDates);
        return factory;
    }

    public EventFactory setLocation(String location) {
        event.setLocation(location);
        return factory;
    }

    public EventFactory setInterests(String... interests) {
        event.setInterests(Arrays.asList(interests));
        return factory;
    }

    public EventFactory setGoing(String... going) {
        event.setGoing(Arrays.asList(going));
        return factory;
    }

    public EventFactory setInterested(String... interested) {
        event.setInterested(Arrays.asList(interested));
        return factory;
    }

    public EventFactory setItems(List<String> items) {
        event.setItems(items);
        return factory;
    }

    public EventFactory setNumberOfAttendees(int numberOfAttendees) {
        event.setNumberOfAttendees(numberOfAttendees);
        return factory;
    }

    public EventFactory setViewRestrictions(String viewRestrictions) {
        event.setViewRestrictions(viewRestrictions);
        return factory;
    }

    public EventFactory setExclusions(List<String> exclusions) {
        event.setExclusions(exclusions);
        return factory;
    }

    public Event build() {
        return event;
    }
}
