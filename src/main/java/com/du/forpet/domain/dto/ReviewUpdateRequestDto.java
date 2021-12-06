package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Reservation;
import lombok.Getter;

@Getter
public class ReviewUpdateRequestDto {

    private float rating;
    private String comment;
    private Reservation reservation;

    public ReviewUpdateRequestDto(float rating, String comment, Reservation reservation) {
        this.rating = rating;
        this.comment = comment;
        this.reservation = reservation;
    }
}
