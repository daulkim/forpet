package com.du.forpet.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {

    @Id @GeneratedValue
    @Column(name="RESERVATION_ID")
    private Long id;

    @Column(name="SERVICE_TYPE")
    private String serviceType;

    @Column(name="START_TIME")
    private LocalDateTime startTime;

    @Column(name="END_TIME")
    private LocalDateTime endTime;

    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name="PET_ID")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name="HELPER_ID")
    private Helper helper;

    @Builder
    public Reservation(String serviceType,
                       LocalDateTime startTime,
                       LocalDateTime endTime,
                       ReservationStatus status,
                       Pet pet) {

        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.pet = pet;
    }

    public void cancel() {

        final LocalDateTime REVOCABLEDATE = LocalDateTime.now().plusDays(1);
        
        if(startTime.isAfter(REVOCABLEDATE)) {
            throw new RuntimeException("예약 하루전에는 취소가 불가합니다.");
        }

        this.setStatus(ReservationStatus.C);
    }

    private void setStatus(ReservationStatus status) {
        this.status = status;
    }
}

enum ReservationStatus{
    /**
     * P: 예약 승인 전
     * R: 예약 승인 완료
     * D: 완료
     * C: 취소
     */

    P,R,D,C
}
