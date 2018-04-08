package com.example.mdenker.interestup;

import java.util.Objects;

public class MeetupCategory {
    private int id;
    private String name;

    MeetupCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof MeetupCategory && this.id == ((MeetupCategory) o).getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
