package com.du.forpet.domain.dto;

import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.entity.ServiceFee;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class ServiceFeeResponseDto {

    private ServiceType serviceType;
    private Integer fee;
    private LocalDate startDate;
    private LocalDate endDate;

    public ServiceFeeResponseDto(ServiceFee serviceFee) {
        this.serviceType = serviceFee.getServiceType();
        this.fee = serviceFee.getFee();
        this.startDate = serviceFee.getStartDate();
        this.endDate = serviceFee.getEndDate();
    }
}
