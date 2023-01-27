package com.example.demo.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeetingRoomTest {

    private final Clock clock = Clock.fixed(Instant.parse("2027-12-22T00:00:00.00Z"), ZoneId.of("UTC"));

    @ParameterizedTest
    @CsvSource({ "100,70", "3,2" })
    void shouldReturnRealCapacityDuringCovid(Integer theoreticalCapacity, Integer expectedCapacityDuringCovid) {
        var meetingRooom = new MeetingRoom("myMeetingRoom", theoreticalCapacity, emptyList(), emptyList());
        var capacityDuringCovid = meetingRooom.getCapacityDuringCovid();
        assertEquals(expectedCapacityDuringCovid, capacityDuringCovid);
    }

    @ParameterizedTest
    @CsvSource({"10, 11", "7,8", "10,12"})
    void shouldBeReady(Integer start, Integer end) {
        var meetingPeriod = getPeriodFromStartAndEnd(8, 9);
        var meetingRoom = new MeetingRoom("myMeetingRoom", 10, emptyList(), List.of(meetingPeriod));

        boolean isMeetingRoomEmptyAndReady = meetingRoom.isReady(getPeriodFromStartAndEnd(start, end));

        assertTrue(isMeetingRoomEmptyAndReady);
    }

    @ParameterizedTest
    @CsvSource({"9,10", "8,11", "9,11", "8,10", "10,11"})
    void shouldNotBeReady(Integer start, Integer end) {
        var meetingPeriod = getPeriodFromStartAndEnd(9,10);
        var meetingRoom = new MeetingRoom("myMeetingRoom", 10, List.of(), List.of(meetingPeriod));

        boolean isMeetingRoomEmpty = meetingRoom.isReady(getPeriodFromStartAndEnd(start, end));

        assertFalse(isMeetingRoomEmpty);
    }

    private Period getPeriodFromStartAndEnd(Integer start, Integer end) {
        return new Period(
                LocalDateTime.of(LocalDate.now(clock), LocalTime.of(start, 0)),
                LocalDateTime.of(LocalDate.now(clock), LocalTime.of(end, 0)));
    }
}
