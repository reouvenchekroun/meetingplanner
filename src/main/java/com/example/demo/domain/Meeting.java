package com.example.demo.domain;

public class Meeting {
    private final String name;
    private final Period period;
    private final MeetingType type;
    private final Integer attendees;

    public Meeting(String name, Period period, MeetingType type, Integer attendees) {
        this.name = name;
        this.period = period;
        this.type = type;
        this.attendees = attendees;
    }

    public String getName() {
        return name;
    }

    public Period getPeriod() {
        return period;
    }

    public MeetingType getType() {
        return type;
    }

    public Integer getAttendees() {
        return attendees;
    }
}
