package com.example.mdenker.interestup;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> implements TabLayout.OnTabSelectedListener {
    private Activity activity;
    private TabLayout tabLayout;
    private Set<String> tabNames;
    private String previousTab;
    private List<Event> events;
    private List<Event> filtered;

    EventAdapter(Activity activity, TabLayout tabLayout) {
        this.activity = activity;
        this.tabLayout = tabLayout;
        this.events = new ArrayList<>();
        this.filtered = new ArrayList<>();

        this.tabNames = new LinkedHashSet<>(Arrays.asList("All", "Going", "Interested"));
        for (String tabName : tabNames) {
            TabLayout.Tab t = tabLayout.newTab();
            t.setText(tabName);
            tabLayout.addTab(t);
        }
    }

    public void setEvents(List<Event> events) {
        this.events.clear();
        this.addEvents(events);
    }

    public void addEvents(List<Event> events) {
        this.events.addAll(events);
        for (Event event : events) {
            for (String interest : event.getInterests()) {
                if (!tabNames.contains(interest)) {
                    tabNames.add(interest);
                    TabLayout.Tab t = tabLayout.newTab();
                    t.setText(interest);
                    tabLayout.addTab(t);
                }
            }
        }
        int selectedTab = tabLayout.getSelectedTabPosition();
        this.onTabSelected(tabLayout.getTabAt(selectedTab));
    }

    public void onEventChanged(Event e) {
        this.notifyItemChanged(filtered.indexOf(e));
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
        ((TextView) holder.itemView.findViewById(R.id.eventInterests)).setText(Util.join(",", e.getInterests()));

        ImageButton interestButton = holder.itemView.findViewById(R.id.home_interest_button);
        if (e.getInterested().contains(User.getFullName())) {
            interestButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star_yellow_36dp));
        } else {
            interestButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_star_border_yellow_36dp));
        }

        ImageButton goingButton = holder.itemView.findViewById(R.id.home_going_button);
        if (e.getGoing().contains(User.getFullName())) {
            goingButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_check_circle_green_36dp));
        } else {
            goingButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_check_green_36dp));
        }

        DateFormat format = new SimpleDateFormat("EEE, MMMM d h:mm a", Locale.US);
        String date = format.format(e.getStartDateTime().getTime()) + " - ";
        ((TextView) holder.itemView.findViewById(R.id.eventStart)).setText(date);

        String time = format.format(e.getEndDateTime().getTime());
        ((TextView) holder.itemView.findViewById(R.id.eventEnd)).setText(time);

        ((TextView) holder.itemView.findViewById(R.id.eventLocation)).setText(e.getLocation());

        List<String> attendeeList = e.getGoing().size() > e.getInterested().size() ? e.getGoing() : e.getInterested();
        String suffix = e.getGoing().size() >= e.getInterested().size() ? "going" : "interested";
        int size = Math.min(4, attendeeList.size());
        String attendees = String.format("%s %s", Util.join(", ", attendeeList.subList(0, size)), suffix);
        ((TextView) holder.itemView.findViewById(R.id.eventAttendees)).setText(attendees);

        holder.itemView.setTag(e.getID());
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String interest = tab.getText() == null ? "" : tab.getText().toString().toLowerCase();
        filtered.clear();
        switch (interest) {
            case "all":
                filtered.addAll(events);
                break;
            case "going":
                for (Event e : events) {
                    if (e.getGoing().contains(User.getFullName())) {
                        filtered.add(e);
                    }
                }
                break;
            case "interested":
                for (Event e : events) {
                    if (e.getInterested().contains(User.getFullName())) {
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
                if (!previousTab.equals(interest) && filtered.size() < 5) {
                    new GetEventsByCategoryTask().execute(interest);
                }
                break;
        }
        this.previousTab = interest;
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
