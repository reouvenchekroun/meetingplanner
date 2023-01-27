package com.example.demo.domain;

import java.util.List;
public class MeetingRoom {
    private final String name;

    private final Integer capacity;

    private final List<Equipment> equipments;

    private List<Period> periods;

    public MeetingRoom(String name, Integer capacity, List<Equipment> equipments, List<Period> periods) {
        this.name = name;
        this.capacity = capacity;
        this.equipments = equipments;
        this.periods = periods;
    }

    public Integer getCapacityDuringCovid() {
        return (int) (capacity * 0.7);
    }

    public boolean containsEquipments(List<Equipment> requiredEquipments) {
        return equipments.containsAll(requiredEquipments);
    }

    public boolean isReady(Period periodToCheck) {
        return periods.stream()
                .noneMatch(period -> {
                    Period periodIncludingCleaningTime = new Period(period.start(), period.end().plusHours(1));
                    return periodIncludingCleaningTime.overlapsWith(periodToCheck);
                });
    }

    public void book(Period periodToBook) {
        periods.add(periodToBook);
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public List<Period> getPeriods() {
        return periods;
    }
}
