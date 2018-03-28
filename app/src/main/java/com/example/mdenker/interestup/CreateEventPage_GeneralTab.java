package com.example.mdenker.interestup;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class CreateEventPage_GeneralTab extends Fragment {

    int tagLimit = 5;

    public static ArrayList<String> tags = new ArrayList<String>();

    ArrayAdapter<String> tagArrayAdapter;
    ArrayAdapter<String> editTagArrayAdapter;

    AutoCompleteTextView addTag;
    GridView gridView;
    GridView gridViewEdit;

    TextView addTagButton;
    ImageButton editTagButton;
    Button cancelTagEditButton;
    Button doneTagEditButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_event_general_tab, container, false);

        addTagButton = (TextView) rootView.findViewById(R.id.add_tag_button);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Collections.addAll(tags,"j");

        tagArrayAdapter = new TagAdapter(getActivity(), tags, this);
        editTagArrayAdapter = new EditableTagAdapter(getActivity(), tags, this);

        gridView = (GridView) view.findViewById(R.id.tag_grid_view);
        gridView.setAdapter(tagArrayAdapter);

        gridViewEdit = (GridView) view.findViewById(R.id.tag_grid_view_edit);
        gridViewEdit.setAdapter(editTagArrayAdapter);


        addTagButton = (TextView) view.findViewById(R.id.add_tag_button);
        editTagButton = (ImageButton) view.findViewById(R.id.edit_tag_button);
        cancelTagEditButton = (Button) view.findViewById(R.id.cancel_edit_button);
        doneTagEditButton = (Button) view.findViewById(R.id.done_edit_button);

        editTagButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gridView.setVisibility(View.GONE);
                gridViewEdit.setVisibility(View.VISIBLE);
                CreateEventPage CreateEventActivity = (CreateEventPage) getActivity();
                CreateEventActivity.OnTagEditClick(v);
            }
        });

        cancelTagEditButton.setOnClickListener(new View.OnClickListener()
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

        doneTagEditButton.setOnClickListener(new View.OnClickListener()
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

        addTag = view.findViewById(R.id.add_tag_field);


        addTagButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (addTag.getText().length() > 2) {
                    if(tags.size() < tagLimit) {
                        tagArrayAdapter.add(addTag.getText().toString());
                        addTag.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Only 5 tags are allowed per event.", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Not long enough to be a valid tag",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        addTag.addTextChangedListener(new TextWatcher() {

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
                            Toast.makeText(getActivity(), "Too many characters in potential tag",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            tagArrayAdapter.add(s.subSequence(0, s.length() - 1).toString());
                            addTag.setText("");
                        }
                    }
                }
                else if (s.length() > 0 && s.charAt(s.length() - 1) == '\n') {
                    addTag.setText(s.subSequence(0, s.length() - 1));
                    Toast.makeText(getActivity(), "Not long enough to be a valid tag",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public ArrayList<String> getTags(){
        return this.tags;
    }

    public void OnEditXClicked(String element) {
        editTagArrayAdapter.remove(element);
        tagArrayAdapter.remove(element);
    }
}
