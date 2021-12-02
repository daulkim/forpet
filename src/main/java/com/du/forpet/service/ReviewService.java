package com.du.forpet.service;

import com.du.forpet.domain.dto.ReviewSaveRequestDto;
import com.du.forpet.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Long save(ReviewSaveRequestDto requestDto) {
        return reviewRepository.save(requestDto.toEntity()).getId();
    }
}
