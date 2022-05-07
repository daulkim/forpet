package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ServiceType extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SERVICE_TYPE_ID")
    private Long id;

    @Column(name="SERVICE_NAME")
    private String serviceName;

    @Column(name="SERVICE_FEE")
    private Integer serviceFee;

    @Builder
    public ServiceType(String serviceName,
                       Integer serviceFee) {
        this.serviceName = serviceName;
        this.serviceFee = serviceFee;
    }
}
