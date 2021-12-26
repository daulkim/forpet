package com.du.forpet.domain.entity;

import com.du.forpet.domain.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class ServiceFee extends BaseTimeEntity{

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_FEE_ID")
    private Long id;

    @Column(name = "SERVICE_TYPE")
    private ServiceType serviceType;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;
}
