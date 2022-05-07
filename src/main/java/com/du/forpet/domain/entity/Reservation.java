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

    @Column(name="RESERVATION_DATE")
    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ReservationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PET_ID")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SERVICE_TYPE_ID")
    private ServiceType serviceType;

    @Builder
    public Reservation(LocalDateTime reservationTime,
                       ReservationStatus status,
                       Pet pet,
                       ServiceType serviceType) {

        this.reservationTime = reservationTime;
        this.status = status;
        this.pet = pet;
        this.serviceType = serviceType;
    }

//    public void cancel(String memo) {
//
//        final LocalDate REVOCABLEDATE = LocalDate.now().plusDays(1);
//
//        if(reservationDate.isBefore(REVOCABLEDATE)) {
//            throw new RuntimeException("예약 취소는 예약일 기준 하루전까지만 가능합니다.");
//        }
//
//        this.status = ReservationStatus.CANCEL;
//        this.memo = memo;
//    }
//
//    public void update(LocalDate reservationDate, LocalTime startTime, LocalTime endTime) {
//
//        final LocalDate REVOCABLEDATE = LocalDate.now().plusDays(1);
//
//        if(!ReservationStatus.updatableStatus(status)) {
//            throw new RuntimeException("예약 변경이 불가능한 상태입니다.");
//        }
//
//        if(this.reservationDate.isBefore(REVOCABLEDATE)) {
//            throw new RuntimeException("예약 변경은 예약일 기준 하루전까지만 가능합니다.");
//        }
//
//        this.startTime = startTime;
//        this.endTime = endTime;
//    }
//
//    public void approve(Long id) {
//        if(!(this.status == ReservationStatus.REQ)) {
//            throw new RuntimeException("예약 승인이 불가능한 상태입니다.");
//        }
//        this.status = ReservationStatus.APPROVE;
//    }
//
//    public void addHistory(ReservationHistory history) {
//        history.setReservation(this);
//        histories.add(history);
//    }
}

