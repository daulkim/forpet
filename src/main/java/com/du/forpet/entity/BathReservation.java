package com.du.forpet.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class BathReservation {

    @Id @GeneratedValue
    @Column(name = "BATH_RESERVATION_ID")
    private Long id;

    private LocalDateTime reservationTime;

    @ManyToOne
    @JoinColumn(name="PET_ID")
    private Pet pet;

}
