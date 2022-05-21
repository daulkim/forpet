package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.*;
import com.du.forpet.service.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class HelperApiController {

    private final HelperService helperService;

    @PostMapping("/api/helpers")
    public Long join(@RequestBody HelperSaveRequestDto helperSaveRequestDto) {
        return helperService.save(helperSaveRequestDto);
    }

//    @GetMapping("/api/helpers/{id}")
//    public HelperResponseDto findById(@PathVariable Long id) {
//        return helperService.findById(id);
//    }
//
//    @PutMapping("/api/helpers/{id}")
//    public Long update(@PathVariable Long id, @RequestBody HelperUpdateRequestDto requestDto) {
//        return helperService.update(id, requestDto);
//    }
//
//    @PatchMapping("/api/helpers/{id}/withdrawal")
//    public void withdraw(@PathVariable Long id) {
//        helperService.withdraw(id);
//    }
//
//    @GetMapping("/api/helpers/{id}/reserved-list")
//    public List<ReservationListResponseDto> findReservationsById(@PathVariable Long id) {
//        return helperService.findReservationsById(id);
//    }
}
