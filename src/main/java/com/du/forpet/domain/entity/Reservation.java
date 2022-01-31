package com.du.forpet.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.ServiceType;
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
    private ServiceType serviceType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="RESERVATION_DATE")
    private LocalDate reservationDate;

    @DateTimeFormat(pattern = "'T'HH:mm")
    @Column(name="START_TIME")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "'T'HH:mm")
    @Column(name="END_TIME")
    private LocalTime endTime;

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

    @OneToOne(mappedBy = "reservation", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Pay pay;

    @OneToMany(mappedBy = "reservation", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<ReservationHistory> histories = new ArrayList<>();

    @Builder
    public Reservation(ServiceType serviceType,
                       LocalDate reservationDate,
                       LocalTime startTime,
                       LocalTime endTime,
                       ReservationStatus status,
                       String memo,
                       Pet pet,
                       Helper helper) {

        this.serviceType = serviceType;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.memo = memo;
        this.pet = pet;
        this.helper = helper;
    }

    public void cancel(String memo) {

        final LocalDate REVOCABLEDATE = LocalDate.now().plusDays(1);

        if(reservationDate.isBefore(REVOCABLEDATE)) {
            throw new RuntimeException("예약 취소는 예약일 기준 하루전까지만 가능합니다.");
        }

        this.status = ReservationStatus.CANCEL;
        this.memo = memo;
    }

    public void update(LocalDate reservationDate, LocalTime startTime, LocalTime endTime) {

        final LocalDate REVOCABLEDATE = LocalDate.now().plusDays(1);

        if(!ReservationStatus.updatableStatus(status)) {
            throw new RuntimeException("예약 변경이 불가능한 상태입니다.");
        }

        if(this.reservationDate.isBefore(REVOCABLEDATE)) {
            throw new RuntimeException("예약 변경은 예약일 기준 하루전까지만 가능합니다.");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void approve(Long id) {
        if(!(this.status == ReservationStatus.REQ)) {
            throw new RuntimeException("예약 승인이 불가능한 상태입니다.");
        }
        this.status = ReservationStatus.APPROVE;
    }

    public void addHistory(ReservationHistory history) {
        history.setReservation(this);
        histories.add(history);
    }
}

