package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.ReviewResponseDto;
import com.du.forpet.domain.dto.ReviewSaveRequestDto;
import com.du.forpet.domain.dto.ReviewUpdateRequestDto;
import com.du.forpet.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reviews")
@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.save(requestDto);
    }

    @GetMapping("/{id}")
    public ReviewResponseDto findById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto) {
        return reviewService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reviewService.delete(id);
    }
}
