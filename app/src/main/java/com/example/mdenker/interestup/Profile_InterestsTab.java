package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class Profile_InterestsTab extends Fragment {

    private ArrayList<String> interests = new ArrayList<String>();
    //private String[] interests = {"Music Performance", "Hiking", "Improv", "Writing",
    //        "Frisbee", "Twilight"};

    ArrayAdapter<String> interestArrayAdapter;
    ArrayAdapter<String> editInterestArrayAdapter;

    AutoCompleteTextView addInterest;
    GridView gridView;
    GridView gridViewEdit;

    TextView plusButton;

    ImageButton editButton;
    Button cancelButton;
    Button doneButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_interests_tab, container, false);

        plusButton = (TextView) rootView.findViewById(R.id.plusButton1);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // ListView listView = (ListView) findViewById(R.id.lvItems);
        //listView.setAdapter(itemsAdapter);

        Collections.addAll(interests,"Music Performance", "Hiking", "Improv", "Writing",
             "Frisbee", "Twilight");

        // have two grids, one gone, with edit version

        interestArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.interest_item, interests);
        editInterestArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.interest_item_edit, interests);

        gridView = (GridView) view.findViewById(R.id.gridView1);
        gridView.setAdapter(interestArrayAdapter);

        gridViewEdit = (GridView) view.findViewById(R.id.gridView1Edit);
        gridViewEdit.setAdapter(editInterestArrayAdapter);

        gridViewEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                parent.removeViewAt(position);
            }
        });
/*
        editButton = (ImageButton) getActivity().findViewById(R.id.editButton);
        cancelButton = (Button) getActivity().findViewById(R.id.cancelButton);
        doneButton = (Button) getActivity().findViewById(R.id.doneButton);

        editButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gridViewEdit.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gridViewEdit.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gridViewEdit.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
            }
        });

*/
        addInterest = view.findViewById(R.id.AddInterest);


        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (addInterest.getText().length() > 3) {
                    interestArrayAdapter.add(addInterest.getText().toString());
                    addInterest.setText("");
                }
            }
        });

        addInterest.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 2) { // at least 0, but 2 for real words
                    if (s.charAt(s.length() - 1) == '\n') {
                        interestArrayAdapter.add(s.subSequence(0, s.length() - 1).toString());
                        addInterest.setText("");
                    }
                }
            }
        });
    }
}