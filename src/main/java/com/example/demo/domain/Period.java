package com.example.demo.domain;

import java.time.LocalDateTime;

public record Period(LocalDateTime start, LocalDateTime end) {

    public boolean overlapsWith(Period period) {
        return !(isBefore(period) || isAfter(period));
    }

    private boolean isAfter(Period period) {
        return period.end().isBefore(start) || period.end().isEqual(start);
    }

    private boolean isBefore(Period period) {
        return period.start().isAfter(end) || period.start().isEqual(end);
    }
}
