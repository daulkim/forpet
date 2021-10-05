package com.du.forpet.dto;

import com.du.forpet.entity.BathReservation;
import com.du.forpet.entity.Pet;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BathReservationSaveRequestDto {

    private String serviceType;
    private LocalDateTime reservationTime;
    private Pet pet;

    @Builder
    public BathReservationSaveRequestDto(String serviceType, LocalDateTime reservationTime, Pet pet) {
        this.serviceType = serviceType;
        this.reservationTime = reservationTime;
        this.pet = pet;

    }

    public BathReservation toEntity() {
        return BathReservation
                        .builder()
                        .serviceType(serviceType)
                        .reservationTime(reservationTime)
                        .pet(pet)
                        .build();
    }

}
