package com.example.mdenker.interestup;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by micha on 3/1/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Event[] events;

    EventAdapter(Event[] events) {
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_view, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((TextView) holder.cardView.findViewById(R.id.eventName)).setText(events[position].getName());
        ((TextView) holder.cardView.findViewById(R.id.eventInterests)).setText("");//events[position].getInterests());
        ((TextView) holder.cardView.findViewById(R.id.eventDate)).setText("");//events[position].getStartDate().toString());
        ((TextView) holder.cardView.findViewById(R.id.eventTime)).setText("");//events[position].getTime());
        ((TextView) holder.cardView.findViewById(R.id.eventLocation)).setText(events[position].getLocation());
        ((TextView) holder.cardView.findViewById(R.id.eventAttendees)).setText("");//events[position].getAttendees());
    }

    @Override
    public int getItemCount() {
        return events.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
