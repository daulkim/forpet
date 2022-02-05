package com.du.forpet.domain.dto;

import com.du.forpet.domain.PayStatus;
import com.du.forpet.domain.entity.Pay;
import com.du.forpet.domain.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaySaveRequestDto {

    private String payType;
    private PayStatus status;
    private Integer price;
    private Reservation reservation;

    @Builder
    public PaySaveRequestDto(String payType,
                             PayStatus status,
                             Integer price,
                             Reservation reservation) {

        this.payType = payType;
        this.status = status;
        this.price = price;
        this.reservation = reservation;

    }

    public Pay toEntity() {
        return Pay.builder()
                    .payType(payType)
                    .status(status)
                    .price(price)
                    .reservation(reservation)
                    .build();
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
