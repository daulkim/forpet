package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.Reservation;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponseDto {

    private ServiceType serviceType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private Pet pet;

    public ReservationResponseDto(Reservation entity) {
        this.serviceType = entity.getServiceType();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.status = entity.getStatus();
        this.pet = entity.getPet();
    }
}
