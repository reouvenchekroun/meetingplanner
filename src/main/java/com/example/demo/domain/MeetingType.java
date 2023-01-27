package com.example.demo.domain;

import java.util.Collections;
import java.util.List;

import static com.example.demo.domain.Equipment.*;

public enum MeetingType {
    VC(List.of(SCREEN, SPEAKERPHONE, WEBCAM)),
    SPEC(List.of(BOARD)),
    RS(Collections.emptyList()),
    RC(List.of(BOARD, SCREEN, SPEAKERPHONE));

    private final List<Equipment> neededEquipment;

    MeetingType(List<Equipment> neededEquipment) {
        this.neededEquipment = neededEquipment;
    }

    public List<Equipment> getNeededEquipment() {
        return neededEquipment;
    }
}
