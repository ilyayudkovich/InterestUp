package com.example.mdenker.interestup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MeetupEvents {
    private MeetupEvent[] events;

    public MeetupEvents() {
        this.events = new MeetupEvent[0];
    }

    public MeetupEvent[] getEvents() {
        return this.events;
    }

    public class MeetupEvent {
        private String name;
        private long duration;
        private String local_date;
        private String local_time;
        private Venue venue;
        private String plain_text_description;
        private Group group;
        private Host[] event_hosts;

        public String getName() {
            return this.name;
        }

        public long getDuration() {
            return this.duration;
        }

        public String getLocalDate() {
            if (this.local_date == null) {
                return new SimpleDateFormat("yyy-MM-dd", Locale.US).format(Calendar.getInstance().getTime());
            }
            return this.local_date;
        }

        public String getLocalTime() {
            if (this.local_time == null) {
                return new SimpleDateFormat("HH:mm", Locale.US).format(Calendar.getInstance().getTime());
            }
            return this.local_time;
        }

        public String getVenue() {
            if (this.venue == null) {
                return "To be determined";
            }
            return this.venue.name;
        }

        public String getDescription() {
            if (this.plain_text_description == null) {
                return "";
            }
            return this.plain_text_description;
        }

        public String getCategory() {
            if (this.group == null) {
                return "Misc";
            }
            return this.group.category.shortname;
        }

        public String getHost() {
            if (this.event_hosts == null) {
                return "Monica Smith";
            }
            return this.event_hosts[0].name;
        }

        private class Group {
            private Category category;

            private class Category {
                private String shortname;
            }
        }

        private class Venue {
            private String name;
        }

        private class Host {
            private String name;
        }
    }
}
