package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class Profile_PersonalInfoTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_personal_info_tab, container, false);
        return rootView;
    }
}