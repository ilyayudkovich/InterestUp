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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class EventDisplayInfo extends Fragment {
    public static final String TAG = "EventDisplayInfo";

    private Context context;
    private Event event;

    private TextView name;
    private TextView tags;
    private TextView host;
    private TextView description;
    private TextView where;
    private TextView start;
    private TextView end;
    private TextView friendsGoing;
    private TextView friendsInterested;
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
        host = view.findViewById(R.id.host);
        description = view.findViewById(R.id.description);
        where = view.findViewById(R.id.where);
        start = view.findViewById(R.id.start);
        end = view.findViewById(R.id.end);
        friendsGoing = view.findViewById(R.id.going);
        friendsInterested = view.findViewById(R.id.interested);
        interestedButton = view.findViewById(R.id.eventInterestedButton);
        goingButton = view.findViewById(R.id.eventGoingButton);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        name.setText(event.getName());
        tags.setText(Util.join(",", event.getInterests()));
        host.setText(String.format("Hosted by %s", event.getHost()));

        DateFormat format = new SimpleDateFormat("EEEE, MMMM d h:mm:a", Locale.US);
        start.setText(format.format(event.getStartDateTime().getTime()));
        end.setText(format.format(event.getEndDateTime().getTime()));

        where.setText(event.getLocation());
        friendsGoing.setText(Util.join(", ", event.getGoing()));
        friendsInterested.setText(Util.join(", ", event.getInterested()));
        description.setText(event.getDescription());

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
