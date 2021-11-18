package com.du.forpet.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESERVATION_ID")
    private Long id;

    @Column(name="SERVICE_TYPE")
    private String serviceType;

    @Column(name="RESERVE_DATE")
    private LocalDate reserveDate;

    @Column(name="START_TIME")
    private LocalTime startTime;

    @Column(name="END_TIME")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PET_ID")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="HELPER_ID")
    private Helper helper;

    @Builder
    public Reservation(String serviceType,
                       LocalDate reserveDate,
                       LocalTime startTime,
                       LocalTime endTime,
                       ReservationStatus status,
                       Pet pet,
                       Helper helper) {

        this.serviceType = serviceType;
        this.reserveDate = reserveDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.pet = pet;
        this.helper = helper;
    }

    public void cancel() {

        final LocalDate REVOCABLEDATE = LocalDate.now().plusDays(1);

        if(reserveDate.isBefore(REVOCABLEDATE)) {
            throw new RuntimeException("예약 취소는 예약일 기준 하루전까지만 가능합니다.");
        }

        this.status = ReservationStatus.C;
    }

    public void update(LocalDate reserveDate, LocalTime startTime, LocalTime endTime) {

        final LocalDate REVOCABLEDATE = LocalDate.now().plusDays(1);

        if(!ReservationStatus.updatableStatus(status)) {
            throw new RuntimeException("예약 변경이 불가능한 상태입니다.");
        }

        if(this.reserveDate.isBefore(REVOCABLEDATE)) {
            throw new RuntimeException("예약 변경은 예약일 기준 하루전까지만 가능합니다.");
        }

        this.reserveDate = reserveDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

