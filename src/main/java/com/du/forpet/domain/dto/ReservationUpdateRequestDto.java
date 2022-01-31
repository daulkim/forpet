package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationUpdateRequestDto {

    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Builder
    public ReservationUpdateRequestDto(LocalDate reservationDate,
                                       LocalTime startTime,
                                       LocalTime endTime) {
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
