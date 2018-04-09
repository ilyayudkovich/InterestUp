package com.example.mdenker.interestup;

import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Switch;
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
    ImageButton cancelTagEditButton;
    ImageButton doneTagEditButton;

    ScrollView scrollView;

    ConstraintLayout scrollableConstraint;

    TextView nameTextView;
    EditText nameEntryField;
    TextView descriptionTextView;
    EditText descriptionEntryField;
    TextView startDateTextView;
    EditText startDateEntryField;
    TextView endDateTextView;
    EditText endDateEntryField;
    TextView startTimeTextView;
    EditText startTimeEntryField;
    TextView endTimeTextView;
    EditText endTimeEntryField;
    Switch tentativeDatesToggle;
    TextView locationTextView;
    EditText locationEntryField;
    TextView tagsTextView;
    EditText tagsEntryField;


    int screenHeight;
    int desiredHeight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_event_general_tab, container, false);

        addTagButton = (TextView) rootView.findViewById(R.id.add_tag_button);

        scrollView = (ScrollView) rootView.findViewById(R.id.CreateEventScroll);
        scrollableConstraint = (ConstraintLayout) rootView.findViewById(R.id.ScrollableConstraint);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        desiredHeight = screenHeight;

        nameTextView = (TextView) rootView.findViewById(R.id.event_name_text_view);
        nameEntryField = (EditText) rootView.findViewById(R.id.event_name_field);
        descriptionTextView = (TextView) rootView.findViewById(R.id.description_text_view);
        descriptionEntryField = (EditText) rootView.findViewById(R.id.description_field);
        startDateTextView = (TextView) rootView.findViewById(R.id.start_date_text_view);
        startDateEntryField = (EditText) rootView.findViewById(R.id.start_date_field);
        endDateTextView = (TextView) rootView.findViewById(R.id.end_date_text_view);
        endDateEntryField = (EditText) rootView.findViewById(R.id.end_date_field);

        startTimeTextView = (TextView) rootView.findViewById(R.id.start_time_text_view);
        startTimeEntryField = (EditText) rootView.findViewById(R.id.start_time_field);
        endTimeTextView = (TextView) rootView.findViewById(R.id.end_time_text_view);
        endTimeEntryField = (EditText) rootView.findViewById(R.id.end_time_field);
        tentativeDatesToggle = (Switch) rootView.findViewById(R.id.tentative_dates_toggle);
        locationTextView = (TextView) rootView.findViewById(R.id.location_text_view);
        locationEntryField = (EditText) rootView.findViewById(R.id.location_field);
        tagsTextView = (TextView) rootView.findViewById(R.id.tags_text_view);
        tagsEntryField = (EditText) rootView.findViewById(R.id.add_tag_field);

        addTagButton = (TextView) rootView.findViewById(R.id.add_tag_button);
        editTagButton = (ImageButton) rootView.findViewById(R.id.edit_tag_button);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Collections.addAll(tags,"j");

        tags.clear();

        nameEntryField.clearFocus();

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(nameEntryField.getWindowToken(), 0);


        tagArrayAdapter = new TagAdapter(getActivity(), tags, this);
        editTagArrayAdapter = new EditableTagAdapter(getActivity(), tags, this);

        gridView = (GridView) view.findViewById(R.id.tag_grid_view);
        gridView.setAdapter(tagArrayAdapter);

        gridViewEdit = (GridView) view.findViewById(R.id.tag_grid_view_edit);
        gridViewEdit.setAdapter(editTagArrayAdapter);

        gridView.setVisibility(View.GONE);
        gridViewEdit.setVisibility(View.VISIBLE);

        addTagButton = (TextView) view.findViewById(R.id.add_tag_button);
        editTagButton = (ImageButton) view.findViewById(R.id.edit_tag_button);
        cancelTagEditButton = (ImageButton) view.findViewById(R.id.cancel_edit_button);
        doneTagEditButton = (ImageButton) view.findViewById(R.id.done_edit_button);


        //NOTE: these aren't doing anything - being overriden by scrollview
        startTimeEntryField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (scrollView.getScrollY() < 100) {
                    scrollView.smoothScrollTo(0, 100);
                }
            }
        });


        endTimeEntryField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            if (scrollView.getScrollY() < 100) {
                scrollView.smoothScrollTo(0, 100);
            }
        }

    });

        tentativeDatesToggle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (scrollView.getScrollY() < 150) {
                    scrollView.smoothScrollTo(0, 150);
                }
            }

        });

        locationEntryField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (scrollView.getScrollY() < 280) {
                    scrollView.smoothScrollTo(0, 280);
                }
            }

        });

        tagsEntryField.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (scrollView.getScrollY() < 350) {
                    scrollView.smoothScrollTo(0, 350);
                }
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

                        if (tags.size() % 2 ==0) {
                            desiredHeight += 75;
                            scrollableConstraint.setMinHeight(desiredHeight);
                        }

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

                            if (tags.size() % 2 ==0) {
                                desiredHeight += 75;
                                scrollableConstraint.setMinHeight(desiredHeight);
                            }
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

        if (tags.size() % 2 ==0) {
            desiredHeight -= 75;
            scrollableConstraint.setMinHeight(desiredHeight);
        }
    }
}
