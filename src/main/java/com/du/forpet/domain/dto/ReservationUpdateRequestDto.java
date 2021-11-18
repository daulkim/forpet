package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationUpdateRequestDto {

    private LocalDate reserveDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @Builder
    public ReservationUpdateRequestDto(LocalDate reserveDate, LocalTime startTime, LocalTime endTime) {
        this.reserveDate = reserveDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
