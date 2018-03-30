package com.example.mdenker.interestup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Jorge Delgado on 2/28/2018.
 */

public class Profile_PersonalInfoTab extends Fragment {

    TextView aboutMe;
    TextView email;
    TextView phone;
    TextView birthday;
    RadioGroup gender;

    Button editButton;
    Button cancelButton;
    Button doneButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_personal_info_tab, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        aboutMe = (TextView) view.findViewById(R.id.aboutMeField);
        email = (TextView) view.findViewById(R.id.emailField);
        phone = (TextView) view.findViewById(R.id.phoneField);
        birthday = (TextView) view.findViewById(R.id.birthdayField);
        gender = (RadioGroup) view.findViewById(R.id.genderRadioField);

        // grab from user
        setFieldsFromUser();
    }

    private void setFieldsFromUser() {
        aboutMe.setText(User.getAboutMe());
        email.setText(User.getEmail());
        phone.setText(User.getPhone());
        birthday.setText(User.getBirthday());
        if (User.getGender() >= 0) {gender.check(User.getGender());}
    }


    public void toggledEdit(boolean enabled) {//, boolean canceled) {
        if (!enabled) { //&& canceled) { // finished editing, so save info
            if (Profile.canceled) { // undoes changes
                setFieldsFromUser();
            }
            else { // updates actual user
                User.setAboutMe(aboutMe.getText().toString());
                User.setEmail(email.getText().toString());
                User.setPhone(phone.getText().toString());
                User.setBirthday(birthday.getText().toString());
                User.setGender(gender.getCheckedRadioButtonId());
            }
        }
    }
}