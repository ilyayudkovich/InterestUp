package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class EventDisplayDiscussion extends Fragment {
    private static final String TAG = "EventDisplayDiscussion";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_display_discussion, container, false);

        return view;
    }
}

