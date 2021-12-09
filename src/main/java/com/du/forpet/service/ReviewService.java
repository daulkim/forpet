package com.du.forpet.service;

import com.du.forpet.domain.dto.ReviewResponseDto;
import com.du.forpet.domain.dto.ReviewSaveRequestDto;
import com.du.forpet.domain.dto.ReviewUpdateRequestDto;
import com.du.forpet.domain.entity.Review;
import com.du.forpet.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Long save(ReviewSaveRequestDto requestDto) {
        boolean isCompleted = requestDto.checkComplete();
        boolean isExist = reviewRepository.countReviewsByReservationId(requestDto.getReservation().getId())>0;

        if(isExist||!isCompleted) throw new RuntimeException("완료된 예약에 대해서 1건의 리뷰만 작성이 가능합니다.");

        return reviewRepository.save(requestDto.toEntity()).getId();
    }

    public ReviewResponseDto findById(Long id) {
        Review review = reviewRepository
                            .findById(id)
                            .orElseThrow(
                                    () -> new IllegalArgumentException("헤당 리뷰가 존재하지 않습니다. id: " + id));
        return new ReviewResponseDto(review);
    }

    public Long update(Long id, ReviewUpdateRequestDto requestDto) {
        Review review = reviewRepository
                            .findById(id)
                            .orElseThrow(
                                    () -> new IllegalArgumentException("헤당 리뷰가 존재하지 않습니다. id: " + id));
        review.update(requestDto.getRating(), requestDto.getComment());
        return id;
    }

    public void delete(Long id) {
        Review review = reviewRepository
                            .findById(id)
                            .orElseThrow(
                                    () -> new IllegalArgumentException("헤당 리뷰가 존재하지 않습니다. id: " + id));
        reviewRepository.delete(review);
    }
}
