package com.example.mdenker.interestup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jorge Delgado on 3/17/2018.
 */


public class EditableExcludePeopleAdapter extends ArrayAdapter<String> {

    private CreateEventPage_AdvancedTab exclude_people_fragment;
    private String editableExcludePeople;

    public EditableExcludePeopleAdapter(Context context, ArrayList<String> editableExcludePeople,
                                        CreateEventPage_AdvancedTab exclude_people_fragment) {
        super(context, 0, editableExcludePeople);
        this.exclude_people_fragment = exclude_people_fragment;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        editableExcludePeople = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exclude_people_item_edit, parent, false);
        }
        // Lookup view for data population
        TextView interestName = (TextView) convertView.findViewById(R.id.exclude_people_name);
        // Populate the data into the template view using the data object
        interestName.setText(editableExcludePeople);
        // Return the completed view to render on screen

        convertView.findViewById(R.id.exclude_people_exit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                exclude_people_fragment.OnEditXClicked(getItem(position));
            }
        });

        return convertView;
    }
}