package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @PostMapping("/api/reservations")
    public Long reserve(@RequestBody ReservationSaveRequestDto reservationReqDto) {
        return reservationService.save(reservationReqDto);
    }

    @GetMapping("/api/reservations/{id}")
    public ReservationResponseDto findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PatchMapping("/api/reservations/{id}")
    public Long changeTime(@PathVariable Long id, @RequestBody ReservationUpdateRequestDto requestDto) {
        return reservationService.update(id, requestDto);
    }

    @PatchMapping("/api/reservations/{id}/cancel")
    public Long cancel(@PathVariable Long id) {
        return reservationService.cancel(id);
    }


}
