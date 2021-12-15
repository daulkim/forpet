package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Point {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POINT_ID")
    private Long id;

    @Column(name="BALANCE")
    private Long balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

    @Builder
    public Point(Long balance, Member member) {
        this.balance = balance;
        this.member = member;
    }
}
