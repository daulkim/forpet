package com.du.forpet.domain.dto;

import com.du.forpet.domain.ServiceType;
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

    private ServiceType serviceType;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Pet pet;
    private Helper helper;

    @Builder
    public ReservationSaveRequestDto(ServiceType serviceType,
                                     LocalDate reservationDate,
                                     LocalTime startTime,
                                     LocalTime endTime,
                                     Pet pet,
                                     Helper helper) {

        this.serviceType = serviceType;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pet = pet;
        this.helper = helper;

    }

    public Reservation toEntity() {

        return Reservation
                        .builder()
                        .serviceType(serviceType)
                        .reservationDate(reservationDate)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status(ReservationStatus.REQ)
                        .pet(pet)
                        .helper(helper)
                        .build();
    }
}
