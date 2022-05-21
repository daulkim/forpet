package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.entity.Address;
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
    private Address from;
    private Address to;
    private Pet pet;
    private ServiceType serviceType;

    @Builder
    public ReservationSaveRequestDto(LocalDateTime reservationDateTime,
                                     Address from,
                                     Address to,
                                     Pet pet,
                                     ServiceType serviceType) {

        this.reservationDateTime = reservationDateTime;
        this.from = from;
        this.to = to;
        this.pet = pet;
        this.serviceType = serviceType;
    }

    public Reservation toEntity() {
        return Reservation.builder()
                            .reservationDateTime(reservationDateTime)
                            .from(from)
                            .to(to)
                            .pet(pet)
                            .serviceType(serviceType)
                            .status(ReservationStatus.REQ)
                            .build();
    }
}
