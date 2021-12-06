package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private float rating;
    private String comment;

    public ReviewResponseDto(Review entity) {
        this.rating = entity.getRating();
        this.comment = entity.getComment();
    }
}
