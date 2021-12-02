package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewSaveRequestDto {

    private float rating;
    private String comment;
    private Reservation reservation;

    public ReviewSaveRequestDto(float rating, String comment, Reservation reservation) {
        this.rating = rating;
        this.comment = comment;
        this.reservation = reservation;
    }

    @Builder
    public Review toEntity() {
        return Review
                .builder()
                .rating(rating)
                .comment(comment)
                .reservation(reservation)
                .build();
    }
}
