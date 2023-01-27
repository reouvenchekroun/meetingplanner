package com.example.demo.controller;

import com.example.demo.domain.MeetingRoom;
import com.example.demo.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;


    @GetMapping("/meeting-rooms")
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRoomRepository.getAllMeetingRooms();
    }
}
