package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationUpdateRequestDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public ReservationUpdateRequestDto(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
