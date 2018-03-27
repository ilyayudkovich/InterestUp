package com.example.mdenker.interestup;

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
        private String description;

        public String getName() {
            return this.name;
        }

        public long getDuration() {
            return duration;
        }

        public String getLocalDate() {
            return local_date;
        }

        public String getLocalTime() {
            return local_time;
        }

        public String getVenue() {
            if (this.venue == null) {
                return "Blackstone Grill";
            }
            return venue.name;
        }

        public String getDescription() {
            return description;
        }

        private class Venue {
            private String name;
        }
    }
}
