package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class CreateEventPage_AdvancedTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_event_advanced_tab, container, false);

        String [] FriendRestrictionValues =
                {"View Restriction","Public", "Friends","Friends of Friends"};

        Spinner FriendRestrictionSpinner = (Spinner) rootView.findViewById(R.id.spinner1);
        ArrayAdapter<String> FriendRestrictionAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, FriendRestrictionValues);
        FriendRestrictionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        FriendRestrictionSpinner.setAdapter(FriendRestrictionAdapter);




        String [] AttendeeValues =
                {"Number of attendees","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
        Spinner AttendeeSpinner = (Spinner) rootView.findViewById(R.id.spinner2);
        ArrayAdapter<String> AttendeeAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, AttendeeValues);
        AttendeeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AttendeeSpinner.setAdapter(AttendeeAdapter);


        return rootView;

    }
}