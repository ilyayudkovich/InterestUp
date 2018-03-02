package com.example.mdenker.interestup;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mdenker on 3/2/18.
 */

public class EditableInterestAdapter extends ArrayAdapter<String> {

    private Profile_InterestsTab interest_fragment;
    private String editableInterest;

    public EditableInterestAdapter(Context context, ArrayList<String> editableInterests,
                                   Profile_InterestsTab interest_fragment) {
        super(context, 0, editableInterests);
        this.interest_fragment = interest_fragment;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        editableInterest = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.interest_item_edit, parent, false);
        }
        // Lookup view for data population
        TextView interestName = (TextView) convertView.findViewById(R.id.interestName);
        // Populate the data into the template view using the data object
        interestName.setText(editableInterest);
        // Return the completed view to render on screen

        convertView.findViewById(R.id.interestExit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                interest_fragment.OnEditXClicked(getItem(position));
            }
        });

        return convertView;
    }
}
