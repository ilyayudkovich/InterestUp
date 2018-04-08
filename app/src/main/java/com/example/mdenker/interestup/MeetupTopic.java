package com.example.mdenker.interestup;

import java.util.Objects;

public class MeetupTopic {
    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof MeetupTopic && this.id == ((MeetupTopic) o).getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
