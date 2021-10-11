package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationSaveRequestDto {

    private String serviceType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private Pet pet;

    @Builder
    public ReservationSaveRequestDto(String serviceType,
                                     LocalDateTime startTime,
                                     LocalDateTime endTime,
                                     Pet pet) {

        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = ReservationStatus.P;
        this.pet = pet;

    }

    public Reservation toEntity() {

        return Reservation
                        .builder()
                        .serviceType(serviceType)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status(status)
                        .pet(pet)
                        .build();
    }
}
