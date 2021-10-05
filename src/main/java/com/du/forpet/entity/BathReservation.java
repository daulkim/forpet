package com.du.forpet.entity;

import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

@Getter
@NoArgsConstructor
@Entity
public class BathReservation {

    @Id @GeneratedValue
    @Column(name = "BATH_RESERVATION_ID")
    private Long id;

    @Column(name="SERVICE_TYPE")
    private String serviceType;

    private LocalDateTime reservationTime;

    private String status;

    @ManyToOne
    @JoinColumn(name="PET_ID")
    private Pet pet;

    @Builder
    public BathReservation(String serviceType, LocalDateTime reservationTime, String status, Pet pet) {
        this.serviceType = serviceType;
        this.reservationTime = reservationTime;
        this.status = status;
        this.pet = pet;
    }

    public void cancel() {

        final LocalDateTime REVOCABLETIME = LocalDateTime.now().plusDays(1);
        
        if(reservationTime.isAfter(REVOCABLETIME)) {
            throw new RuntimeException("예약 하루전에는 취소가 불가합니다.");
        }

        this.setStatus("cancel");
    }

    private void setStatus(String status) {
        this.status = status;
    }
}
