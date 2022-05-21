package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
