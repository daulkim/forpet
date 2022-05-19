package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.ServiceType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ServiceTypeResponseDto {

    private Long id;
    private String serviceName;

    @Builder
    public ServiceTypeResponseDto(ServiceType entity) {
        this.id = entity.getId();
        this.serviceName = entity.getServiceName();
    }
}
