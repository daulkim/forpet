package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.ServiceType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationSaveRequestDto {

    private LocalDateTime reservationTime;
    private Pet pet;
    private ServiceType serviceType;

    @Builder
    public ReservationSaveRequestDto(LocalDateTime reservationTime,
                                     Pet pet,
                                     ServiceType serviceType) {
        this.reservationTime = reservationTime;
        this.pet = pet;
        this.serviceType = serviceType;
    }

    public Reservation toEntity() {
        return Reservation.builder()
                            .reservationTime(reservationTime)
                            .pet(pet)
                            .serviceType(serviceType)
                            .status(ReservationStatus.REQ)
                            .build();
    }
}
