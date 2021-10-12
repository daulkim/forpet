package com.du.forpet.controller;

import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reservation")
@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public Long reserve(ReservationSaveRequestDto requestDto) {
        return reservationService.save(requestDto);
    }

    @GetMapping("/{id}")
    public ReservationResponseDto findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PutMapping("/{id}")
    public Long changeTime(@PathVariable Long id, ReservationUpdateRequestDto requestDto) {
        return reservationService.update(id, requestDto);
    }

    @PatchMapping("/status/{id}")
    public void cancel(@PathVariable Long id) {
        reservationService.cancel(id);
    }

}
