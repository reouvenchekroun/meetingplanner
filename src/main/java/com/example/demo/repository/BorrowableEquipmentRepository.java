package com.example.demo.repository;

import com.example.demo.domain.BorrowableEquipment;
import com.example.demo.domain.Equipment;
import com.example.demo.domain.Period;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.domain.Equipment.SCREEN;
import static com.example.demo.domain.Equipment.SPEAKERPHONE;
import static com.example.demo.domain.Equipment.WEBCAM;

@Repository
public class BorrowableEquipmentRepository {

    private final List<BorrowableEquipment> borrowableEquipments = List.of(
            new BorrowableEquipment(SPEAKERPHONE, new ArrayList<>()),
            new BorrowableEquipment(SPEAKERPHONE, new ArrayList<>()),
            new BorrowableEquipment(SPEAKERPHONE, new ArrayList<>()),
            new BorrowableEquipment(SPEAKERPHONE, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>()),
            new BorrowableEquipment(WEBCAM, new ArrayList<>()),
            new BorrowableEquipment(WEBCAM, new ArrayList<>()),
            new BorrowableEquipment(WEBCAM, new ArrayList<>()),
            new BorrowableEquipment(WEBCAM, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>()),
            new BorrowableEquipment(SCREEN, new ArrayList<>())
    );

    public List<BorrowableEquipment> getAllBorrowableEquipments() {
        return borrowableEquipments;
    }

    public List<BorrowableEquipment> getAvailableBorrowableEquipments(Period period) {
        return borrowableEquipments.stream()
                .filter(eq -> eq.isAvailable(period))
                .collect(Collectors.toList());
    }

    public Optional<BorrowableEquipment> findAvailableBorrowableEquipment(Period period, Equipment equipment) {
        return borrowableEquipments.stream()
                .filter(borrowableEquipment -> matchesEquipmentTypeAndIsAvailable(period, equipment, borrowableEquipment))
                .findFirst();
    }

    private boolean matchesEquipmentTypeAndIsAvailable(Period period, Equipment equipment, BorrowableEquipment borrowableEquipment) {
        return borrowableEquipment.getEquipment().equals(equipment) && borrowableEquipment.isAvailable(period);
    }

}
