package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Address;
import com.du.forpet.domain.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationRequestList {

    private Long reservationId;
    private LocalDateTime reservationDateTime;
    private Address from;
    private Address to;
    private String pet;
    private String serviceName;
    private Long serviceFee;

    @Builder
    public ReservationRequestList(Reservation entity) {
        this.reservationId = entity.getId();
        this.reservationDateTime = entity.getReservationDateTime();
        this.from = entity.getFrom();
        this.to = entity.getTo();
        this.pet = entity.getPet().getPetName();
        this.serviceName = entity.getServiceType().getServiceName();
        this.serviceFee = entity.getServiceType().getServiceFee();
    }
}
