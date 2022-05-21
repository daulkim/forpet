package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ReservationResponseDto {

    private Long id;
    private ServiceType serviceType;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private String from;
    private String to;
    private String status;
    private String petName;

    public ReservationResponseDto(Reservation entity) {
        this.id = entity.getId();
        this.serviceType = entity.getServiceType();
        this.reservationDate = entity.getReservationDateTime().toLocalDate();
        this.reservationTime = entity.getReservationDateTime().toLocalTime();
        this.from = entity.getFrom().getAddress1() + entity.getFrom().getAddress2();
        this.to = entity.getTo().getAddress1() + entity.getTo().getAddress2();
        this.status = entity.getStatus().toString();
        this.petName = entity.getPet().getPetName();
    }
}
