package com.du.forpet.domain.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESERVATION_ID")
    private Long id;

    @Column(name="RESERVATION_DATETIME")
    private LocalDateTime reservationDateTime;

    @Column(name = "FROM_ADDRESS")
    private String from;

    @Column(name = "TO_ADDRESS")
    private String to;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PET_ID")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SERVICE_NAME")
    private ServiceType serviceType;

    @Builder
    public Reservation(LocalDateTime reservationDateTime,
                       ReservationStatus status,
                       String from,
                       String to,
                       Pet pet,
                       ServiceType serviceType) {

        this.reservationDateTime = reservationDateTime;
        this.status = status;
        this.from = from;
        this.to = to;
        this.pet = pet;
        this.serviceType = serviceType;
    }

    public void update(LocalDateTime reservationDateTime) {

        if(!ReservationStatus.updatableStatus(status)) {
            throw new RuntimeException("예약 변경이 불가능한 상태입니다.");
        }

        this.reservationDateTime = reservationDateTime;
    }

    public void cancel() {

        if(!ReservationStatus.updatableStatus(status)) {
            throw new RuntimeException("예약 취소가 불가능한 상태입니다.");
        }

        this.status = ReservationStatus.CANCEL;
    }
}

