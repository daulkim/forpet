package com.du.forpet.domain.dto;

import com.du.forpet.domain.ServiceType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ServiceFeeUpdateRequestDto {

    private ServiceType serviceType;
    private Integer fee;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public ServiceFeeUpdateRequestDto(ServiceType serviceType,
                                      Integer fee,
                                      LocalDate startDate,
                                      LocalDate endDate) {

        this.serviceType = serviceType;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
