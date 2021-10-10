package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.Pet;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationSaveRequestDto {

    private String serviceType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Pet pet;

    @Builder
    public ReservationSaveRequestDto(String serviceType,
                                     LocalDateTime startTime,
                                     LocalDateTime endTime,
                                     Pet pet) {

        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pet = pet;

    }

    public Reservation toEntity() {

        return Reservation
                        .builder()
                        .serviceType(serviceType)
                        .startTime(startTime)
                        .endTime(endTime)
                        .pet(pet)
                        .build();
    }
}
