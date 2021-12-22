package com.du.forpet.domain.entity;

import com.du.forpet.domain.PayStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Pay extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_ID")
    private Long id;

    private String payType;

    private PayStatus status;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    @Builder
    public Pay(PayStatus status, String payType, Long price, Reservation reservation) {
        this.status = status;
        this.payType = payType;
        this.price = price;
        this.reservation = reservation;
    }

    public void update() {
        this.status = PayStatus.CANCEL;
    }
}