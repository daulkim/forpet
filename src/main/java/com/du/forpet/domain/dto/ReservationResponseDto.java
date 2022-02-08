package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.Reservation;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationResponseDto {

    private Long id;
    private ServiceType serviceType;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ReservationStatus status;
    private Pet pet;

    public ReservationResponseDto(Reservation entity) {
        this.id = entity.getId();
        this.serviceType = entity.getServiceType();
        this.reservationDate = entity.getReservationDate();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.status = entity.getStatus();
        this.pet = entity.getPet();
    }
}
