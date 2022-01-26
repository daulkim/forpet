package com.du.forpet.controller.api;

import com.du.forpet.domain.dto.ServiceFeeResponseDto;
import com.du.forpet.domain.dto.ServiceFeeSaveRequestDto;
import com.du.forpet.domain.dto.ServiceFeeUpdateRequestDto;
import com.du.forpet.service.ServiceFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fees")
@RequiredArgsConstructor
@RestController
public class ServiceFeeApiController {

    private final ServiceFeeService serviceFeeService;

    @PostMapping()
    public Long save(@RequestBody ServiceFeeSaveRequestDto requestDto) {
        return serviceFeeService.save(requestDto);
    }

    @GetMapping()
    public List<ServiceFeeResponseDto> list() {
        return serviceFeeService.findEffectiveAll();
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody ServiceFeeUpdateRequestDto updateDto) {
        return serviceFeeService.update(id, updateDto);
    }
}