package com.example.mdenker.interestup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MeetupEvents {
    private List<MeetupEvent> events;

    public MeetupEvents() {
        this.events = new ArrayList<>();
    }

    public List<MeetupEvent> getEvents() {
        return this.events;
    }

    public class MeetupEvent {
        private String id;
        private String name;
        private long duration;
        private String local_date;
        private String local_time;
        private Venue venue;
        private String plain_text_description;
        private Group group;
        private List<Host> event_hosts;

        public long getId() {
            return Long.parseLong(this.id, 36);
        }

        public String getName() {
            return this.name;
        }

        public long getDuration() {
            return this.duration;
        }

        public String getLocalDate() {
            if (this.local_date == null) {
                return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().getTime());
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

        public MeetupCategory getCategory() {
            if (this.group == null || this.group.category == null) {
                return new MeetupCategory(-1, "Other");
            }
            return this.group.category;
        }

        public List<String> getTopics() {
            List<String> topics = new ArrayList<>();
            if (this.group != null && this.group.topics != null) {
                for (Group.Topic topic : this.group.topics) {
                    topics.add(topic.name);
                }
            }
            return topics;
        }

        public String getHost() {
            if (this.event_hosts == null) {
                return "Monica Smith";
            }
            return this.event_hosts.get(0).name;
        }

        class Group {
            private MeetupCategory category;
            private List<Topic> topics;

            class Topic {
                private String name;
            }
        }

        class Venue {
            private String name;
        }

        class Host {
            private String name;
        }
    }
}