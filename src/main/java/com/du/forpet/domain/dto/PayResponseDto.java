package com.du.forpet.domain.dto;

import com.du.forpet.domain.PayStatus;
import com.du.forpet.domain.entity.Pay;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayResponseDto {

    private String payType;

    private PayStatus status;

    private Integer price;

    public PayResponseDto(Pay entity) {
        this.payType = entity.getPayType();
        this.status = entity.getStatus();
        this.price = entity.getPrice();
    }
}
