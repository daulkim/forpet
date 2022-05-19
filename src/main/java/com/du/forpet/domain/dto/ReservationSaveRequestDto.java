package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.ServiceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationSaveRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime reservationDateTime;
    private Pet pet;
    private ServiceType serviceType;

    @Builder
    public ReservationSaveRequestDto(LocalDateTime reservationDateTime,
                                     Pet pet,
                                     ServiceType serviceType) {
        this.reservationDateTime = reservationDateTime;
        this.pet = pet;
        this.serviceType = serviceType;
    }

    public Reservation toEntity() {
        return Reservation.builder()
                            .reservationDateTime(reservationDateTime)
                            .pet(pet)
                            .serviceType(serviceType)
                            .status(ReservationStatus.REQ)
                            .build();
    }
}
