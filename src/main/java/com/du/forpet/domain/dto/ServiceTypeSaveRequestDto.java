package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.ServiceType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ServiceTypeSaveRequestDto {

    private String serviceName;
    private Long serviceFee;

    @Builder
    public ServiceTypeSaveRequestDto(String serviceName,
                                     Long serviceFee) {
        this.serviceName = serviceName;
        this.serviceFee = serviceFee;
    }

    public ServiceType toEntity() {
        return ServiceType.builder()
                            .serviceName(serviceName)
                            .serviceFee(serviceFee)
                            .build();
    }
}
