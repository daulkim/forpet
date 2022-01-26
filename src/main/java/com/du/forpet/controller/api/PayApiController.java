package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.PayResponseDto;
import com.du.forpet.domain.dto.PaySaveRequestDto;
import com.du.forpet.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pay")
@RequiredArgsConstructor
@RestController
public class PayApiController {

    private final PayService payService;

    @PostMapping()
    public Long pay(@RequestBody PaySaveRequestDto payDto) {
        return payService.save(payDto);
    }

    @GetMapping("/{id}")
    public PayResponseDto findById(@PathVariable Long id) {
        return payService.findById(id);
    }

    @PatchMapping("/{id}")
    public Long cancel(@PathVariable Long id) {
        return payService.cancel(id);
    }
}
