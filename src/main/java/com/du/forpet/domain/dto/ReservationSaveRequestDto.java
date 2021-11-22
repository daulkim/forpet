package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ReservationSaveRequestDto {

    private String serviceType;
    private LocalDate reserveDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;
    private Pet pet;
    private Helper helper;

    @Builder
    public ReservationSaveRequestDto(String serviceType,
                                     LocalDate reserveDate,
                                     LocalTime startTime,
                                     LocalTime endTime,
                                     Pet pet,
                                     Helper helper) {

        this.serviceType = serviceType;
        this.reserveDate = reserveDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = ReservationStatus.PRE;
        this.pet = pet;
        this.helper = helper;

    }

    public Reservation toEntity() {

        return Reservation
                        .builder()
                        .serviceType(serviceType)
                        .reserveDate(reserveDate)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status(status)
                        .pet(pet)
                        .helper(helper)
                        .build();
    }
}
