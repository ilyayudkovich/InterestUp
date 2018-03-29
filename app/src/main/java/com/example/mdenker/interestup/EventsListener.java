package com.example.mdenker.interestup;

import java.util.List;

public interface EventsListener {

    /**
     * Notifies listeners when events have been added.
     * @param events The list of added events.
     */
    void onEventsAdded(List<Event> events);

    /**
     * Notifies listeners when events have been fetched.
     * @param events The list of fetched events.
     */
    void onEventsFetched(List<Event> events);

    /**
     * Notifies listeners when the given event was changed.
     * @param e The changed event.
     */
    void onEventChanged(Event e);
}
