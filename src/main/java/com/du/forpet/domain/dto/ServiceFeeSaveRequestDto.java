package com.du.forpet.domain.dto;

import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.entity.ServiceFee;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ServiceFeeSaveRequestDto {

    private ServiceType serviceType;
    private Integer price;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public ServiceFeeSaveRequestDto(ServiceType serviceType,
                                    Integer price,
                                    LocalDate startDate,
                                    LocalDate endDate) {
        this.serviceType = serviceType;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public ServiceFee toEntity() {
        return ServiceFee
                .builder()
                .serviceType(serviceType)
                .price(price)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
