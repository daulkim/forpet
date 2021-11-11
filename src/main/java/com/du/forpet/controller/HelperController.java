package com.du.forpet.controller;

import com.du.forpet.domain.dto.*;
import com.du.forpet.service.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/helpers")
@RequiredArgsConstructor
@RestController
public class HelperController {

    private final HelperService helperService;

    @PostMapping()
    public Long join(@RequestBody HelperSaveRequestDto requestDto, @RequestBody HelperScheduleSaveRequestDto scheduleRequestDto) {
        return helperService.save(requestDto, scheduleRequestDto);
    }

    @GetMapping("/{id}")
    public HelperResponseDto findById(@PathVariable Long id) {
        return helperService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody HelperUpdateRequestDto requestDto) {
        return helperService.update(id, requestDto);
    }

    @PatchMapping("/{id}/withdrawal")
    public void withdraw(@PathVariable Long id) {
        helperService.withdraw(id);
    }

    @GetMapping("/{id}/reserved-list")
    public List<ReservationListResponseDto> findReservationsById(@PathVariable Long id) {
        return helperService.findReservationsById(id);
    }
}
