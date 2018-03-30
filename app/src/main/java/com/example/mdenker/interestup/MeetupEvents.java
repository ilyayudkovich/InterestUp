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
            return this.local_date;
        }

        public String getLocalTime() {
            return this.local_time;
        }

        public String getVenue() {
            if (this.venue == null) {
                return "To be determined";
            }
            return this.venue.name;
        }

        public String getDescription() {
            return this.plain_text_description;
        }

        public String getCategory() {
            return this.group.category.shortname;
        }

        public String getHost() {
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
