package com.example.mdenker.interestup;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by micha on 3/1/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements TabLayout.OnTabSelectedListener {
    private Activity activity;
    private List<Event> events;
    private List<Event> filtered;

    EventAdapter(Activity activity, List<Event> events) {
        this.activity = activity;
        this.events = events;
        this.filtered = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_view, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Event e = filtered.get(position);
        ((TextView) holder.itemView.findViewById(R.id.eventName)).setText(e.getName());

        StringBuilder interests = new StringBuilder(e.getInterests().get(0));
        for (int i = 1; i < e.getInterests().size(); i++) {
            interests.append(", ");
            interests.append(e.getInterests().get(i));
        }
        ((TextView) holder.itemView.findViewById(R.id.eventInterests)).setText(interests.toString());

        ImageButton interestButton = holder.itemView.findViewById(R.id.home_interest_button);
        if (e.getInterested().contains(Database.user)) {
            interestButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star_yellow_36dp));
        } else {
            interestButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star_border_yellow_36dp));
        }

        ImageButton b = holder.itemView.findViewById(R.id.home_going_button);
        if (e.getGoing().contains(Database.user)) {
            b.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_check_circle_green_36dp));
        } else {
            b.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_check_green_36dp));
        }

        DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d", Locale.US);
        String date = dateFormat.format(e.getStartDateTime().getTime()) + " - " +
                dateFormat.format(e.getEndDateTime().getTime());
        ((TextView) holder.itemView.findViewById(R.id.eventDate)).setText(date);

        DateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        String time = timeFormat.format(e.getStartDateTime().getTime()) + " - " +
                timeFormat.format(e.getEndDateTime().getTime());
        ((TextView) holder.itemView.findViewById(R.id.eventTime)).setText(time);

        ((TextView) holder.itemView.findViewById(R.id.eventLocation)).setText(e.getLocation());

        List<String> attendeeList = e.getGoing().size() > e.getInterested().size() ? e.getGoing() : e.getInterested();
        String suffix = e.getGoing().size() > e.getInterested().size() ? "going" : "interested";
        StringBuilder attendees = new StringBuilder(e.getHost());
        for (int i = 0; i < Math.min(3, attendeeList.size()); i++) {
            attendees.append(", ");
            attendees.append(attendeeList.get(i));
        }
        attendees.append(" ").append(suffix);
        ((TextView) holder.itemView.findViewById(R.id.eventAttendees)).setText(attendees.toString());

        holder.itemView.setTag(e);
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String interest = tab.getText() == null ? "" : tab.getText().toString().toLowerCase();
        filtered = new ArrayList<>();
        switch (interest) {
            case "all":
                filtered.addAll(events);
                break;
            case "going":
                for (Event e : events) {
                    if (e.getGoing().contains(Database.user)) {
                        filtered.add(e);
                    }
                }
                break;
            case "interested":
                for (Event e : events) {
                    if (e.getInterested().contains(Database.user)) {
                        filtered.add(e);
                    }
                }
                break;
            default:
                for (Event e : events) {
                    for (String i : e.getInterests()) {
                        if (i.toLowerCase().equals(interest)) {
                            filtered.add(e);
                        }
                    }
                }
                break;
        }
        this.notifyDataSetChanged();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(CardView cardView) {
            super(cardView);
        }
    }
}
