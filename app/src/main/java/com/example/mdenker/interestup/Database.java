package com.example.mdenker.interestup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by micha on 3/16/2018.
 */

public class Database {
    public static final String user = "Jordan You";

    public static List<Event> events = Arrays.asList(
            EventFactory.create().setName("Hiking Trip")
                    .setHost("Danielle Keller")
                    .setInterests("Hiking", "Outdoors")
                    .setLocation("Blue Mountains")
                    .addGoing("John Duke", "Liza McCall", "Rob Holt").build(),
            EventFactory.create().setName("Walk the Moon")
                    .setHost("Annie Gomez")
                    .setInterests("Concerts", "Music")
                    .setLocation("Blue Hills Bank Pavilion")
                    .addGoing("Joan Chen", "Doug Nichols", "Maria Yano")
                    .addInterested("Hayden Carter").build(),
            EventFactory.create().setName("Weekend Camping")
                    .setHost("Sam Tanner")
                    .setInterests("Outdoors", "Camping")
                    .setLocation("Red Hills")
                    .addGoing("Madison Conti").build(),
            EventFactory.create().setName("Hair")
                    .setHost("Angelo Fiorello")
                    .setInterests("Theatre", "Music")
                    .addInterested("Sarah Fowler")
                    .setLocation("Studio Theatre").build());
}
