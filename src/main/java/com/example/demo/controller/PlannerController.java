package com.example.demo.controller;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingRoom;
import com.example.demo.dto.MeetingDto;
import com.example.demo.mapper.MeetingMapper;
import com.example.demo.service.NoAvailableMeetingRoomException;
import com.example.demo.service.PlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlannerController {

    @Autowired
    private PlannerService plannerService;

    @Autowired
    private MeetingMapper meetingMapper;


    @PostMapping("/find-meeting-room")
    @ResponseBody
    public MeetingRoom findMeetingRoom(@RequestBody MeetingDto meetingDto) throws NoAvailableMeetingRoomException {
        var meeting = meetingMapper.meetingDtoToMeeting(meetingDto);
        return plannerService.findMeetingRoom(meeting);
    }
}
