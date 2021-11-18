package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.Reservation;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationResponseDto {

    private String serviceType;
    private LocalDate reserveDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;
    private Pet pet;

    public ReservationResponseDto(Reservation entity) {
        this.serviceType = entity.getServiceType();
        this.reserveDate = entity.getReserveDate();
        this.endTime = entity.getEndTime();
        this.status = entity.getStatus();
        this.pet = entity.getPet();
    }
}
