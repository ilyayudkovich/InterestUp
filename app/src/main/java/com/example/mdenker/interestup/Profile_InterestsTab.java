package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mdenker on 2/28/2018.
 */

public class Profile_InterestsTab extends Fragment {

    private ArrayList<Integer> interestsIndexesRemoved = new ArrayList<Integer>();
    private String[] preservedInterests;
    ArrayAdapter<String> interestArrayAdapter;
    ArrayAdapter<String> editInterestArrayAdapter;

    AutoCompleteTextView addInterest;
    GridView gridView;
    GridView gridViewEdit;

    TextView plusButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_interests_tab, container, false);

        plusButton = (TextView) rootView.findViewById(R.id.plusButton1);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        interestArrayAdapter = new InterestAdapter(getActivity(), User.getInterests(), this);
        editInterestArrayAdapter = new EditableInterestAdapter(getActivity(), User.getInterests(), this);

        gridView = (GridView) view.findViewById(R.id.gridView1);
        gridView.setAdapter(interestArrayAdapter);

        gridViewEdit = (GridView) view.findViewById(R.id.gridView1Edit);
        gridViewEdit.setAdapter(editInterestArrayAdapter);

        addInterest = view.findViewById(R.id.AddInterest);

        plusButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (addInterest.getText().length() > 2) {
                    User.addInterest(addInterest.getText().toString());
                    addInterest.setText("");
                }
                else {
                    Toast.makeText(getActivity(), "Not long enough to be a valid interest",
                            Toast.LENGTH_LONG).show();
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
                if (s.length() > 3) { // at least 0, but 2 for real words - last \n
                    if (s.charAt(s.length() - 1) == '\n') {
                        if (s.length() > 22) {
                            Toast.makeText(getActivity(), "Too many characters in potential interest",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            //interestArrayAdapter.add(s.subSequence(0, s.length() - 1).toString());
                            User.addInterest(s.subSequence(0, s.length() - 1).toString());
                            addInterest.setText("");
                        }
                    }
                }
                else if (s.length() > 0 && s.charAt(s.length() - 1) == '\n') {
                    addInterest.setText(s.subSequence(0, s.length() - 1));
                    Toast.makeText(getActivity(), "Not long enough to be a valid interest",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private int ArrayFind(String[] array, String element) {
        int index = 0;
        for(String s: array){
            if(s.equals(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void OnEditXClicked(String element) {

        int indexRemoved = ArrayFind(preservedInterests, element);
        interestsIndexesRemoved.add(indexRemoved);

        editInterestArrayAdapter.remove(element);
        interestArrayAdapter.remove(element); // I removing it from here also removes it from the user
    }

    public void toggledEdit(boolean enabled) {//, boolean canceled) {
        if (!enabled) {
            gridViewEdit.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
            if (Profile.canceled) { // undoes interest changes

                Collections.sort(interestsIndexesRemoved);

                for(int i = 0; i < interestsIndexesRemoved.size(); i++) {
                    int insertionIndex = interestsIndexesRemoved.get(i);
                    String interest = preservedInterests[insertionIndex];
                    User.addInterest(interest, insertionIndex);
                }
                editInterestArrayAdapter.notifyDataSetChanged();
                interestArrayAdapter.notifyDataSetChanged();

                Profile.canceled = false;
            }
            else { // updates changes in user to make permanent
                /* doesn't seem to be needed, already being removed
                for(String interest : interestsToRemove) {
                    User.removeInterest(interest);
                }
                */
            }
            interestsIndexesRemoved.clear();
        }
        else {
            preservedInterests = new String[User.getInterests().size()];
            User.getInterests().toArray(preservedInterests); // TODO bug where adding interest while in edit mode and then deleting it causes crash

            gridView.setVisibility(View.GONE);
            gridViewEdit.setVisibility(View.VISIBLE);
        }
    }
}