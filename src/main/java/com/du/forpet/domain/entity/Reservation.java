package com.du.forpet.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor
@Entity
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESERVATION_ID")
    private Long id;

    @Column(name="SERVICE_TYPE")
    private String serviceType;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="START_TIME")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="END_TIME")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ReservationStatus status;

    @Column(name="MEMO")
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PET_ID")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="HELPER_ID")
    private Helper helper;

    @Builder
    public Reservation(String serviceType,
                       LocalDateTime startTime,
                       LocalDateTime endTime,
                       ReservationStatus status,
                       String memo,
                       Pet pet,
                       Helper helper) {

        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.memo = memo;
        this.pet = pet;
        this.helper = helper;
    }

    public void cancel(String memo) {

        final LocalDateTime REVOCABLEDATE = LocalDateTime.now().plusDays(1);

        if(startTime.isBefore(REVOCABLEDATE)) {
            throw new RuntimeException("예약 취소는 예약일 기준 하루전까지만 가능합니다.");
        }

        this.status = ReservationStatus.CANCEL;
        this.memo = memo;
    }

    public void update(LocalDateTime startTime, LocalDateTime endTime) {

        final LocalDateTime REVOCABLEDATE = LocalDateTime.now().plusDays(1);

        if(!ReservationStatus.updatableStatus(status)) {
            throw new RuntimeException("예약 변경이 불가능한 상태입니다.");
        }

        if(this.startTime.isBefore(REVOCABLEDATE)) {
            throw new RuntimeException("예약 변경은 예약일 기준 하루전까지만 가능합니다.");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void approve() {
        if(!(this.status == ReservationStatus.REQ)) {
            throw new RuntimeException("예약 승인이 불가능한 상태입니다.");
        }
        this.status = ReservationStatus.APPROVE;
    }
}

