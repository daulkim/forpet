package com.du.forpet.domain.entity;

import com.du.forpet.domain.ServiceType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class ServiceFee extends BaseTimeEntity {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_FEE_ID")
    private Long id;

    @Column(name = "SERVICE_TYPE")
    private ServiceType serviceType;

    @Column(name = "FEE")
    private Integer fee;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Builder
    public ServiceFee(ServiceType serviceType,
                      Integer fee,
                      LocalDate startDate,
                      LocalDate endDate) {
        this.serviceType = serviceType;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean effectiveDate() {
        LocalDate now = LocalDate.now();
        return 0<=this.startDate.compareTo(now) && this.endDate.compareTo(now)<=0;
    }

    public void update(ServiceType serviceType,
                       Integer fee,
                       LocalDate startDate,
                       LocalDate endDate) {

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
