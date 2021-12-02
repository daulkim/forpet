package com.du.forpet.controller;

import com.du.forpet.domain.dto.ReviewSaveRequestDto;
import com.du.forpet.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.save(requestDto);
    }
}
