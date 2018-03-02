package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class EventDisplayDiscussion extends Fragment {
    private static final String TAG = "EventDisplayDiscussion";

    private EditText mname1;
    private EditText mname2;
    private EditText mname3;
    private EditText mname4;
    private EditText mcomment1;
    private EditText mcomment2;
    private EditText mcomment3;
    private EditText mcomment4;


    private String name1    = "John";
    private String name2    = "Mackenzie";
    private String name3    = "Sam";
    private String name4    = "LeHaim";
    private String comment1 = "This sounds like a great event";
    private String comment2 = "thanks to HCI at NEU, or else never would've heard bout this";
    private String comment3 = "That class is great!";
    private String comment4 = "I love their teacher";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_display_discussion, container, false);

        mname1 = (EditText)view.findViewById(R.id.name1);
        mname2 = (EditText)view.findViewById(R.id.name2);
        mname3 = (EditText)view.findViewById(R.id.name3);
        mname4 = (EditText)view.findViewById(R.id.name4);

        mcomment1 = (EditText)view.findViewById(R.id.comment1);
        mcomment1 = (EditText)view.findViewById(R.id.comment2);
        mcomment1 = (EditText)view.findViewById(R.id.comment3);
        mcomment1 = (EditText)view.findViewById(R.id.comment4);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mname1.setText(name1);
        mname2.setText(name2);
        mname3.setText(name3);
        mname4.setText(name4);

        mcomment1.setText(comment1);
        mcomment2.setText(comment2);
        mcomment3.setText(comment3);
        mcomment4.setText(comment4);
    }
}

