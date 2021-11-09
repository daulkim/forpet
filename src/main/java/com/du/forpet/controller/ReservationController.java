package com.du.forpet.controller;

import com.du.forpet.domain.dto.ReservationListResponseDto;
import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reservation")
@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public Long reserve(@RequestBody ReservationSaveRequestDto requestDto) {
        return reservationService.save(requestDto);
    }

    @GetMapping("/{id}")
    public ReservationResponseDto findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PutMapping("/{id}")
    public Long changeTime(@PathVariable Long id, @RequestBody ReservationUpdateRequestDto requestDto) {
        return reservationService.update(id, requestDto);
    }

    @PatchMapping("/status/{id}")
    public void cancel(@PathVariable Long id) {
        reservationService.cancel(id);
    }

//    @GetMapping("/{email}")
//    public List<ReservationListResponseDto> findReservations(@PathVariable String email) {
//        return reservationService.findByHelperEmail(email);
//    }
}
