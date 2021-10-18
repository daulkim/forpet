package com.du.forpet.controller;

import com.du.forpet.domain.dto.HelperResponseDto;
import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.domain.dto.HelperUpdateRequestDto;
import com.du.forpet.service.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/helper")
@RequiredArgsConstructor
@RestController
public class HelperController {

    private final HelperService helperService;

    @PostMapping("/")
    public Long join(HelperSaveRequestDto requestDto) {
        return helperService.save(requestDto);
    }

    @GetMapping("/{id}")
    public HelperResponseDto findById(@PathVariable Long id) {
        return helperService.findById(id);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, HelperUpdateRequestDto requestDto) {
        return helperService.update(id, requestDto);
    }

    @PatchMapping("/status/{id}")
    public void resign(@PathVariable Long id) {
        helperService.delete(id);
    }
}
