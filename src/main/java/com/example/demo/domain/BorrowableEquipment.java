package com.example.demo.domain;

import java.util.List;

public class BorrowableEquipment {

    private Equipment equipment;
    private List<Period> periods;

    public BorrowableEquipment(Equipment equipment, List<Period> periods) {
        this.equipment = equipment;
        this.periods = periods;
    }

    public boolean isAvailable(Period newPeriod) {
        return periods.stream().filter(period -> period.overlapsWith(newPeriod)).count() == 0;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public List<Period> getUsePeriods() {
        return periods;
    }
}
