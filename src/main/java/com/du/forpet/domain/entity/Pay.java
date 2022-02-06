package com.du.forpet.domain.entity;

import com.du.forpet.domain.PayStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Pay extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_ID")
    private Long id;

    private String payType;

    private PayStatus status;

    private Integer price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    @OneToMany(mappedBy = "pay")
    private List<PayHistory> histories = new ArrayList<>();

    @Builder
    public Pay(PayStatus status, String payType, Integer price, Reservation reservation) {
        this.status = status;
        this.payType = payType;
        this.price = price;
        this.reservation = reservation;
    }

    public void update() {
        this.status = PayStatus.CANCEL;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void addHistory(PayHistory history) {
        history.setPay(this);
        histories.add(history);
    }
}