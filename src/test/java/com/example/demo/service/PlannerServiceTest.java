package com.example.demo.service;

import com.example.demo.domain.Equipment;
import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingRoom;
import com.example.demo.domain.MeetingType;
import com.example.demo.domain.Period;
import com.example.demo.repository.MeetingRoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlannerServiceTest {

    @Mock
    private MeetingRoomRepository meetingRoomRepository;

    @InjectMocks
    private PlannerService plannerService;

    private final List<MeetingRoom> meetingsRooms = new ArrayList<>();

    private final Clock clock = Clock.fixed(Instant.parse("2027-12-22T00:00:00.00Z"), ZoneId.of("UTC"));


    @ParameterizedTest
    @CsvSource({"SPEC, room3", "RS, room1"})
    public void shouldFindMeetingRoom(MeetingType meetingType, String expectedMeetingRoomName) throws NoAvailableMeetingRoomException {
        initMeetingRooms();
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingsRooms);
        Meeting meeting = new Meeting("myMeeting" ,getPeriodFromStartAndEnd(9,10), meetingType, 5);
        var foundMeetingRoom = plannerService.findMeetingRoom(meeting);
        assertEquals(foundMeetingRoom.getName(),(expectedMeetingRoomName));
    }

    @Test
    public void shouldNotBeAbleToBookAMeetingRoomTwiceWithSameDate() throws NoAvailableMeetingRoomException {
        initMeetingRooms();
        when(meetingRoomRepository.getAllMeetingRooms()).thenReturn(meetingsRooms);
        Meeting meeting = new Meeting("myMeeting" ,getPeriodFromStartAndEnd(9,10), MeetingType.RS, 5);
        var foundMeetingRoom1 = plannerService.findMeetingRoom(meeting);
        var foundMeetingRoom2 = plannerService.findMeetingRoom(meeting);
        assertNotEquals(foundMeetingRoom1.getName(),foundMeetingRoom2.getName());
    }

    private Period getPeriodFromStartAndEnd(Integer start, Integer end) {
        return new Period(
                LocalDateTime.of(LocalDate.now(clock), LocalTime.of(start, 0)),
                LocalDateTime.of(LocalDate.now(clock), LocalTime.of(end, 0)));
    }

    private void initMeetingRooms() {
        meetingsRooms.add(new MeetingRoom("room1", 10, List.of(Equipment.SCREEN), new ArrayList<>()));
        meetingsRooms.add(new MeetingRoom("room2", 10, List.of(Equipment.WEBCAM), new ArrayList<>()));
        meetingsRooms.add(new MeetingRoom("room3", 10, List.of(Equipment.BOARD), new ArrayList<>()));
    }
}
