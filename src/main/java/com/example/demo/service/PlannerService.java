package com.example.demo.service;


import com.example.demo.domain.BorrowableEquipment;
import com.example.demo.domain.Equipment;
import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingRoom;
import com.example.demo.repository.BorrowableEquipmentRepository;
import com.example.demo.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlannerService {
    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @Autowired
    private BorrowableEquipmentRepository borrowableEquipmentRepository;

    public MeetingRoom findMeetingRoom(Meeting meeting) throws NoAvailableMeetingRoomException {
        var meetingRooms = meetingRoomRepository.getAllMeetingRooms();
        var availableMeetingRooms = retrieveAvailableMeetingRooms(meeting, meetingRooms);

        Optional<MeetingRoom> meetingRoomWithRequiredEquipments = availableMeetingRooms.stream()
                .filter(meetingRoom -> meetingRoom.containsEquipments(meeting.getType().getNeededEquipment()))
                .findFirst();

        if (meetingRoomWithRequiredEquipments.isPresent()) {
            meetingRoomWithRequiredEquipments.get().book(meeting.getPeriod());
            return meetingRoomWithRequiredEquipments.get();
        }

        for(MeetingRoom meetingRoom : availableMeetingRooms) {
            List<BorrowableEquipment> equipmentsToBorrow = new ArrayList<>();
            var missingEquipments = getMissingEquipments(meetingRoom.getEquipments(), meeting.getType().getNeededEquipment());
            missingEquipments.forEach(missingEquipment ->
                    borrowableEquipmentRepository.findAvailableBorrowableEquipment(meeting.getPeriod(), missingEquipment)
                            .ifPresent(equipmentsToBorrow::add));

            if(missingEquipments.size() == equipmentsToBorrow.size()) {
                equipmentsToBorrow.forEach(eq -> eq.getUsePeriods().add(meeting.getPeriod()));
                meetingRoom.book(meeting.getPeriod());
                return meetingRoom;
            }
        }

        throw new NoAvailableMeetingRoomException("No equipment required available at the moment.");
    }

    private List<MeetingRoom> retrieveAvailableMeetingRooms(Meeting meeting, List<MeetingRoom> meetingRooms) throws NoAvailableMeetingRoomException {
        var availableMeetingRooms = meetingRooms.stream()
                .filter(meetingRoom -> meeting.getAttendees() <= meetingRoom.getCapacityDuringCovid())
                .filter(meetingRoom -> meetingRoom.isReady(meeting.getPeriod()))
                .collect(Collectors.toList());

        if(availableMeetingRooms.isEmpty()) {
            throw new NoAvailableMeetingRoomException("No room with enough capacity has been found for meeting " + meeting.getName());
        }
        return availableMeetingRooms;
    }

    private List<Equipment> getMissingEquipments(List<Equipment> availableEquipments, List<Equipment> neededEquipments) {
        return neededEquipments.stream()
                .filter(eq -> !availableEquipments.contains(eq))
                .collect(Collectors.toList());
    }

}
