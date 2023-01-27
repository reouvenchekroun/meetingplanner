package com.example.demo.repository;

import com.example.demo.domain.MeetingRoom;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.domain.Equipment.BOARD;
import static com.example.demo.domain.Equipment.SCREEN;
import static com.example.demo.domain.Equipment.SPEAKERPHONE;
import static com.example.demo.domain.Equipment.WEBCAM;
import static java.util.Collections.emptyList;

@Repository
public class MeetingRoomRepository {

    private final List<MeetingRoom> meetingRooms = List.of(
            new MeetingRoom("E1001", 23, emptyList(), new ArrayList<>()),
            new MeetingRoom("E1002", 10, List.of(SCREEN), new ArrayList<>()),
            new MeetingRoom("E1003", 8, List.of(SPEAKERPHONE), new ArrayList<>()),
            new MeetingRoom("E1004", 4, List.of(BOARD), new ArrayList<>()),
            new MeetingRoom("E2001", 4, emptyList(), new ArrayList<>()),
            new MeetingRoom("E2002", 15, List.of(SCREEN, WEBCAM), new ArrayList<>()),
            new MeetingRoom("E2003", 7, emptyList(), new ArrayList<>()),
            new MeetingRoom("E2004", 9, List.of(BOARD), new ArrayList<>()),
            new MeetingRoom("E3001", 13, List.of(SCREEN, WEBCAM, SPEAKERPHONE), new ArrayList<>()),
            new MeetingRoom("E3002", 8, emptyList(), new ArrayList<>()),
            new MeetingRoom("E3003", 9, List.of(SCREEN, SPEAKERPHONE), new ArrayList<>()),
            new MeetingRoom("E3004", 4, emptyList(), new ArrayList<>())
    );

    public List<MeetingRoom> getAllMeetingRooms() {
        return meetingRooms;
    }
}
