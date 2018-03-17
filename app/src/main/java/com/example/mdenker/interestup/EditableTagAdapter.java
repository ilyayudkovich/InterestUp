package com.example.mdenker.interestup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rosef on 3/17/2018.
 */

public class EditableTagAdapter extends ArrayAdapter<String> {

    private CreateEventPage_GeneralTab tag_fragment;
    private String editableTag;

    public EditableTagAdapter(Context context, ArrayList<String> editableTags,
                              CreateEventPage_GeneralTab tag_fragment) {
        super(context, 0, editableTags);
        this.tag_fragment = tag_fragment;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        editableTag = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tag_item_edit, parent, false);
        }
        // Lookup view for data population
        TextView interestName = (TextView) convertView.findViewById(R.id.tag_name);
        // Populate the data into the template view using the data object
        interestName.setText(editableTag);
        // Return the completed view to render on screen

        convertView.findViewById(R.id.tag_exit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tag_fragment.OnEditXClicked(getItem(position));
            }
        });

        return convertView;
    }
}