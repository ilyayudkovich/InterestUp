package com.example.mdenker.interestup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class EventDisplayInfo extends Fragment {
    public static final String TAG = "EventDisplayInfo";

    Event event;

    private TextView name;
    private TextView where;
    private TextView address;
    private TextView time;
    private TextView when;
    private TextView totalGoing;
    private TextView toBring;


    public EventDisplayInfo() {}

    @SuppressLint("ValidFragment")
    public EventDisplayInfo(Event event) {
        this.event = event;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_display_info, container, false);
        name       = (TextView)view.findViewById(R.id.name);
        where      = (TextView)view.findViewById(R.id.where);
        address    = (TextView)view.findViewById(R.id.address);
        time       = (TextView)view.findViewById(R.id.time);
        when       = (TextView)view.findViewById(R.id.when);
        totalGoing = (TextView)view.findViewById(R.id.totalGoing);
        toBring    = (TextView)view.findViewById(R.id.toBring);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        name.setText(event.getName());
        where.setText(event.getLocation());
        address.setText(event.getLocation());
        time.setText(event.getStartDateTime().getTime().toString());
        when.setText(event.getStartDateTime().getTime().toString());
        totalGoing.setText(Integer.toString(event.getNumberOfAttendees()));
        toBring.setText("Shoes, boots, chapstick, rainjacket");
    }
}
