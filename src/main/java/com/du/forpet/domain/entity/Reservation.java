package com.du.forpet.domain.entity;

import java.time.LocalDateTime;

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

    @Column(name="START_TIME")
    private LocalDateTime startTime;

    @Column(name="END_TIME")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
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

