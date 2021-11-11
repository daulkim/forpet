package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationSaveRequestDto {

    private String serviceType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private Pet pet;
    private Helper helper;

    @Builder
    public ReservationSaveRequestDto(String serviceType,
                                     LocalDateTime startTime,
                                     LocalDateTime endTime,
                                     Pet pet,
                                     Helper helper) {

        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = ReservationStatus.P;
        this.pet = pet;
        this.helper = helper;

    }

    public Reservation toEntity() {

        return Reservation
                        .builder()
                        .serviceType(serviceType)
                        .startTime(startTime)
                        .endTime(endTime)
                        .status(status)
                        .pet(pet)
                        .helper(helper)
                        .build();
    }
}
