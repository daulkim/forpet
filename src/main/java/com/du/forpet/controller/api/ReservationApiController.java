package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/reservations")
@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @PostMapping()
    public Long reserve(@RequestBody ReservationSaveRequestDto requestDto) {
        System.out.println(requestDto.getServiceType().toString());
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

    @PatchMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id, String memo) {
        reservationService.cancel(id, memo);
    }

    @PatchMapping("/{id}/approval")
    public void approval(@PathVariable Long id) {
        reservationService.approve(id);
    }
}
