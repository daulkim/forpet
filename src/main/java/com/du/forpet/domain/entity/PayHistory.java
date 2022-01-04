package com.du.forpet.domain.entity;

import com.du.forpet.domain.PayStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class PayHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_HISTORY_ID")
    private Long id;

    private String payType;

    private PayStatus status;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PAY_ID")
    private Pay pay;
}
