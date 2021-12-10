package com.du.forpet.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long id;

    @Column(name="RATING")
    private float rating;

    @Column(name="COMMENT")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Reservation reservation;

    @Builder
    public Review(float rating, String comment, Reservation reservation) {
        this.rating = rating;
        this.comment = comment;
        this.reservation = reservation;
    }

    public void update(float rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
}
