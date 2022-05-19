package com.du.forpet.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationUpdateRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime reservationDateTime;

    @Builder
    public ReservationUpdateRequestDto(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }
}