package com.example.mdenker.interestup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class CreateEventPage_AdvancedTab extends Fragment {

    private ArrayList<String> tags = new ArrayList<String>();

    ArrayAdapter<String> excludePeopleArrayAdapter;
    ArrayAdapter<String> editExcludePeopleArrayAdapter;

    AutoCompleteTextView addExcludePeople;
    GridView gridView;
    GridView gridViewEdit;

    TextView addExcludePeopleButton;
    ImageButton editExcludePeopleButton;
    Button cancelExcludePeopleEditButton;
    Button doneExcludePeopleEditButton;

    String eventName;
    EditText eventNameInput;
    Button createEventButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_event_advanced_tab, container, false);

        addExcludePeopleButton = (TextView) rootView.findViewById(R.id.add_people_exclude_button);

        String [] FriendRestrictionValues =
                {"View Restriction","Public", "Friends","Friends of Friends"};

        Spinner FriendRestrictionSpinner = (Spinner) rootView.findViewById(R.id._view_restriction_field);
        ArrayAdapter<String> FriendRestrictionAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, FriendRestrictionValues);
        FriendRestrictionAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        FriendRestrictionSpinner.setAdapter(FriendRestrictionAdapter);




        String [] AttendeeValues =
                {"Number of attendees","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
        Spinner AttendeeSpinner = (Spinner) rootView.findViewById(R.id.number_of_attendees_field);
        ArrayAdapter<String> AttendeeAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, AttendeeValues);
        AttendeeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AttendeeSpinner.setAdapter(AttendeeAdapter);


        return rootView;

    }

    @SuppressLint("WrongViewCast")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Collections.addAll(tags,"j");

        excludePeopleArrayAdapter = new ExcludePeopleAdapter(getActivity(), tags, this);
        editExcludePeopleArrayAdapter = new EditableExcludePeopleAdapter(getActivity(), tags, this);

        gridView = (GridView) view.findViewById(R.id.exclude_people_grid_view);
        gridView.setAdapter(excludePeopleArrayAdapter);

        gridViewEdit = (GridView) view.findViewById(R.id.exclude_people_grid_view_edit);
        gridViewEdit.setAdapter(editExcludePeopleArrayAdapter);


        addExcludePeopleButton = (TextView) view.findViewById(R.id.add_people_exclude_button);
        editExcludePeopleButton = (ImageButton) view.findViewById(R.id.edit_people_exclude_button);
        cancelExcludePeopleEditButton = (Button) view.findViewById(R.id.cancel_people_exclude_edit_button);
        doneExcludePeopleEditButton = (Button) view.findViewById(R.id.done_people_exclude_edit_button);

        editExcludePeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                gridView.setVisibility(View.GONE);
                gridViewEdit.setVisibility(View.VISIBLE);
                CreateEventPage CreateEventActivity = (CreateEventPage) getActivity();
                CreateEventActivity.OnTagEditClick(v);
            }
        });

        cancelExcludePeopleEditButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gridViewEdit.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                CreateEventPage CreateEventActivity = (CreateEventPage) getActivity();
                CreateEventActivity.OnTagEditClick(v);
            }
        });

        doneExcludePeopleEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                gridViewEdit.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
                CreateEventPage CreateEventActivity = (CreateEventPage) getActivity();
                CreateEventActivity.OnTagEditClick(v);
            }
        });

        addExcludePeople = view.findViewById(R.id.add_exclude_people_field);

        addExcludePeopleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (addExcludePeople.getText().length() > 2) {
                    excludePeopleArrayAdapter.add(addExcludePeople.getText().toString());
                    addExcludePeople.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "Not long enough to be a valid person",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        addExcludePeople.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 3) { // at least 0, but 2 for real words - last \n
                    if (s.charAt(s.length() - 1) == '\n') {
                        if (s.length() > 22) {
                            Toast.makeText(getActivity(), "Too many characters in potentially excluded person",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            excludePeopleArrayAdapter.add(s.subSequence(0, s.length() - 1).toString());
                            addExcludePeople.setText("");
                        }
                    }
                }
                else if (s.length() > 0 && s.charAt(s.length() - 1) == '\n') {
                    addExcludePeople.setText(s.subSequence(0, s.length() - 1));
                    Toast.makeText(getActivity(), "Not long enough to be a valid person",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void OnEditXClicked(String element) {
        editExcludePeopleArrayAdapter.remove(element);
        excludePeopleArrayAdapter.remove(element);
    }
}