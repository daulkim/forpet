package com.du.forpet.domain.dto;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.entity.Reservation;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class ReservationListResponseDto {

    private Long id;
    private LocalTime startTime;
    private ReservationStatus status;
    private String memberName;

    public ReservationListResponseDto(Reservation entity) {
        this.id = entity.getId();
        this.startTime = entity.getStartTime();
        this.status = entity.getStatus();
        this.memberName = entity.getPet().getMember().getName();
    }
}
