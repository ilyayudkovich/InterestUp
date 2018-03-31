package com.example.mdenker.interestup;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

import java.util.Collections;
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

    Drawable backgroundAboutMe;
    Drawable backgroundEmail;
    Drawable backgroundPhone;
    Drawable backgroundBirthday;

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

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Profile profile_activity = (Profile) getActivity();
                if (!profile_activity.getEnabled() && group.getCheckedRadioButtonId() != -1) { // can't change unless in edit mode
                    group.check(User.getGender());
                }
            }
        });


        backgroundAboutMe = aboutMe.getBackground();
        backgroundEmail = email.getBackground();
        backgroundPhone = phone.getBackground();
        backgroundBirthday = birthday.getBackground();

        aboutMe.setBackground(new ColorDrawable(Color.TRANSPARENT));
        email.setBackground(new ColorDrawable(Color.TRANSPARENT));
        phone.setBackground(new ColorDrawable(Color.TRANSPARENT));
        birthday.setBackground(new ColorDrawable(Color.TRANSPARENT));
    }

    private void setFieldsFromUser() {
        aboutMe.setText(User.getAboutMe());
        email.setText(User.getEmail());
        phone.setText(User.getPhone());
        birthday.setText(User.getBirthday());
        if (User.getGender() >= 0) {gender.check(User.getGender());}
    }


    public void toggledEdit(boolean enabled) {
        if (!enabled) {
            aboutMe.setBackground(new ColorDrawable(Color.TRANSPARENT));
            email.setBackground(new ColorDrawable(Color.TRANSPARENT));
            phone.setBackground(new ColorDrawable(Color.TRANSPARENT));
            birthday.setBackground(new ColorDrawable(Color.TRANSPARENT));
            //gender.setBackground(new ColorDrawable(Color.TRANSPARENT));
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
        else {
            aboutMe.setBackground(backgroundAboutMe);
            email.setBackground(backgroundEmail);
            phone.setBackground(backgroundPhone);
            birthday.setBackground(backgroundBirthday);
            //gender.setBackground(null);
        }
    }
}