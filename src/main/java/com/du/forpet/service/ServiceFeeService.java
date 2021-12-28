package com.du.forpet.service;

import com.du.forpet.domain.dto.ServiceFeeSaveRequestDto;
import com.du.forpet.repository.ServiceFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceFeeService {

    private final ServiceFeeRepository serviceFeeRepository;


    public Long save(ServiceFeeSaveRequestDto requestDto) {
        return serviceFeeRepository.save(requestDto.toEntity()).getId();
    }
}
