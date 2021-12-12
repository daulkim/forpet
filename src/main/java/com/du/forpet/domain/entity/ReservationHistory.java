package com.du.forpet.domain.entity;

import com.du.forpet.domain.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ReservationHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name="RESERVATION_ID")
    private Reservation reservation;

    @Builder
    public ReservationHistory(ReservationStatus status, Reservation reservation) {
        this.status = status;
        this.reservation = reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
