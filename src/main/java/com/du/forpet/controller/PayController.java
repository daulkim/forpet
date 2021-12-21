package com.du.forpet.controller;

import com.du.forpet.domain.dto.PayResponseDto;
import com.du.forpet.domain.dto.PaySaveRequestDto;
import com.du.forpet.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PayController {

    private final PayService payService;

    @PostMapping("/pay")
    public Long pay(@RequestBody PaySaveRequestDto payDto) {
       return payService.save(payDto);
    }

    @GetMapping("/pay/{id}")
    public PayResponseDto findById(@PathVariable Long id) {
        return payService.findById(id);
    }
}
