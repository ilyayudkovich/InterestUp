package com.example.mdenker.interestup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Ilya Yudkovich on 2/28/2018.
 */

public class User {
    private static String firstName = "Bob"; // not being used in profile yet
    private static String lastName = "Sagat";
    private static ArrayList<String> interests = new ArrayList<String>();

    public static boolean initialized = false;

    private static String aboutMe = "MY BOBSIDFSFO";
    private static String email;
    private static String phone;
    private static String birthday = "01/01/1990"; //new Date(1990, 1, 1);
    private static int gender = -1; // what index in the radiobutton

    public User() {
        //this.firstName = firstName;
        //this.lastName = lastName;
        // default starting interests
        if (!initialized) { // adds default interests - could grab from storage or database irl
            Collections.addAll(interests, "Music Performance", "Hiking", "Improv", "Writing",
                    "Frisbee", "Twilight");
            initialized = true;
        }
    }

    public static void addInterest(String interest) {interests.add(interest);}
    public static void addInterest(String interest, int index) {interests.add(index, interest);}

    public static void removeInterest(String element) {
        interests.remove(element);
    }

    public static ArrayList<String> getInterests() {
        return interests;
    }

    public static String getFirstName() { return firstName; }
    public static String getLastName() { return lastName; }
    public static void setFirstName(String name) { firstName = name; }
    public static void setLastName(String name) { lastName = name; }

    public static String getAboutMe() { return aboutMe; }
    public static String getEmail() { return email; }
    public static String getPhone() { return phone; }
    public static String  getBirthday() { return birthday; }
    public static int getGender() { return gender; }

    public static void setAboutMe(String newAboutMe) { aboutMe = newAboutMe; }
    public static void setEmail(String newEmail) { email = newEmail; }
    public static void setPhone(String newPhone) { phone = newPhone; }
    public static void setBirthday(String newBirthday) { birthday = newBirthday; }
    public static void setGender(int newGender) { gender = newGender; }
}
