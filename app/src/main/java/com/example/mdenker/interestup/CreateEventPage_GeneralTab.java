package com.example.mdenker.interestup;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class CreateEventPage_GeneralTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_event_general_tab, container, false);
        return rootView;
    }
}
