package com.example.mdenker.interestup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by micha on 3/16/2018.
 */

public class Database {
    public static final String user = "Jordan You";

    public static List<Event> events = Arrays.asList(
            EventFactory.create().setId(0)
                    .setName("Hiking Trip")
                    .setHost("Danielle Keller")
                    .setInterests("Hiking", "Outdoors")
                    .setLocation("Blue Mountains")
                    .addGoing("John Duke", "Liza McCall", "Rob Holt").build(),
            EventFactory.create().setId(1)
                    .setName("Walk the Moon")
                    .setHost("Annie Gomez")
                    .setInterests("Concerts", "Music")
                    .setLocation("Blue Hills Bank Pavilion")
                    .addGoing("Joan Chen", "Doug Nichols", "Maria Yano")
                    .addInterested("Hayden Carter").build(),
            EventFactory.create().setId(2)
                    .setName("Weekend Camping")
                    .setHost("Sam Tanner")
                    .setInterests("Outdoors", "Camping")
                    .setLocation("Red Hills")
                    .addGoing("Madison Conti").build(),
            EventFactory.create().setId(3)
                    .setName("Hair")
                    .setHost("Angelo Fiorello")
                    .setInterests("Theatre", "Music")
                    .addInterested("Sarah Fowler")
                    .setLocation("Studio Theatre").build());

    public static Event getEvent(int id) {
        for (Event e : events) {
            if (e.getID() == id) {
                return e;
            }
        }
        return null;
    }
}
