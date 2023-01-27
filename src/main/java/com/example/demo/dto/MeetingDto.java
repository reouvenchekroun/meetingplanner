package com.example.demo.dto;

public class MeetingDto {
    private String name;
    private String type;
    private String date;
    private Integer start;
    private Integer end;
    private Integer attendees;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer getAttendees() {
        return attendees;
    }
}
