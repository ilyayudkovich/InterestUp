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

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class EventDisplayInfo extends Fragment {
    public static final String TAG = "EventDisplayInfo";

    Event event;

    private EditText where;
    private EditText address;
    private EditText time;
    private EditText when;
    private EditText totalGoing;
    private EditText toBring;


    public EventDisplayInfo() {}

    @SuppressLint("ValidFragment")
    public EventDisplayInfo(Event event) {
        this.event = event;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_display_info, container, false);
        where      = (EditText)view.findViewById(R.id.where);
        address    = (EditText)view.findViewById(R.id.address);
        time       = (EditText)view.findViewById(R.id.time);
        when       = (EditText)view.findViewById(R.id.when);
        totalGoing = (EditText)view.findViewById(R.id.totalGoing);
        toBring    = (EditText)view.findViewById(R.id.toBring);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        where.setText(event.getLocation());
        address.setText(event.getLocation());
        time.setText(event.getStartDateTime().getTime().toString());
        when.setText(event.getStartDateTime().getTime().toString());
        totalGoing.setText(Integer.toString(event.getNumberOfAttendees()));
        toBring.setText("Shoes, boots, chapstick, rainjacket");
    }
}
