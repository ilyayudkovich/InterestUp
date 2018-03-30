package com.example.mdenker.interestup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class EventDisplayInfo extends Fragment {
    public static final String TAG = "EventDisplayInfo";

    private Context context;
    private Event event;

    private TextView name;
    private TextView tags;
    private TextView description;
    private TextView where;
    private TextView time;
    private TextView when;
    private TextView totalGoing;
    private TextView toBring;
    private ImageButton interestedButton;
    private ImageButton goingButton;


    public EventDisplayInfo() {}

    @SuppressLint("ValidFragment")
    public EventDisplayInfo(Context context, Event event) {
        this.context = context;
        this.event = event;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_display_info, container, false);
        name = view.findViewById(R.id.name);
        tags = view.findViewById(R.id.eventTags);
        description = view.findViewById(R.id.description);
        where = view.findViewById(R.id.where);
        time = view.findViewById(R.id.time);
        when = view.findViewById(R.id.when);
        totalGoing = view.findViewById(R.id.totalGoing);
        toBring = view.findViewById(R.id.toBring);
        interestedButton = view.findViewById(R.id.eventInterestedButton);
        goingButton = view.findViewById(R.id.eventGoingButton);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        name.setText(event.getName());
        tags.setText(String.join(",", event.getInterests()));
        description.setText(event.getDescription());
        where.setText(event.getLocation());
        time.setText(event.getStartDateTime().getTime().toString());
        when.setText(event.getStartDateTime().getTime().toString());
        totalGoing.setText(Integer.toString(event.getNumberOfAttendees()));
        toBring.setText("Shoes, boots, chapstick, rainjacket");

        if (event.getInterested().contains(User.getFullName())) {
            interestedButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_yellow_36dp));
        } else {
            interestedButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_yellow_36dp));
        }

        if (event.getGoing().contains(User.getFullName())) {
            goingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_36dp));
        } else {
            goingButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_green_36dp));
        }
    }

    public void onInterestedClick(View view) {
        interestedButton = view.findViewById(R.id.eventInterestedButton);
        if (event.getInterested().contains(User.getFullName())) {
            event.removeInterested(User.getFullName());
            interestedButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_border_yellow_36dp));
        } else {
            event.addInterested(User.getFullName());
            interestedButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_star_yellow_36dp));
        }
    }

    public void onGoingClick(View view) {
        goingButton = view.findViewById(R.id.eventGoingButton);
        if (event.getGoing().contains(User.getFullName())) {
            event.removeGoing(User.getFullName());
            goingButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_green_36dp));
        } else {
            event.addGoing(User.getFullName());
            goingButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_circle_green_36dp));
        }
    }
}
