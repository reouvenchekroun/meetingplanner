package com.example.demo.mapper;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingType;
import com.example.demo.domain.Period;
import com.example.demo.dto.MeetingDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MeetingMapper {

    public Meeting meetingDtoToMeeting(MeetingDto dto) {
        var start = dto.getStart();
        var end = dto.getEnd();
        var dateValues = dto.getDate().split("-");

        int years = Integer.parseInt(dateValues[0]);
        int months = Integer.parseInt(dateValues[1]);
        int days = Integer.parseInt(dateValues[2]);
        var localDate = LocalDate.of(years, months, days);
        Period period = new Period(LocalDateTime.of(localDate, LocalTime.of(start,0)),
        LocalDateTime.of(localDate, LocalTime.of(end,0)));
        return new Meeting(
                dto.getName(),
                period,
                MeetingType.valueOf(dto.getType()),
                dto.getAttendees());

    }
}
