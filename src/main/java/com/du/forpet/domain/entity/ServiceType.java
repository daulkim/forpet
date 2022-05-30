package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class ServiceType extends BaseTimeEntity {

    @Id
    @Column(name="SERVICE_NAME")
    private String serviceName;

    @Column(name="SERVICE_FEE")
    private Long serviceFee;

    @Builder
    public ServiceType(String serviceName,
                       Long serviceFee) {
        this.serviceName = serviceName;
        this.serviceFee = serviceFee;
    }

    public static Set<ServiceType> serviceTypeList(Set<ServiceType> serviceTypes) {
        Set<ServiceType> serviceTypeSet = new HashSet<>();
        serviceTypes.forEach(serviceType -> serviceTypeSet.add(new ServiceType(serviceType)));
        return serviceTypeSet;
    }

    public ServiceType(ServiceType serviceType) {
        this.serviceName = serviceType.getServiceName();
    }
}
