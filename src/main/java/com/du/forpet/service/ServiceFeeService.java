package com.du.forpet.service;

import com.du.forpet.domain.dto.ServiceFeeResponseDto;
import com.du.forpet.domain.dto.ServiceFeeSaveRequestDto;
import com.du.forpet.repository.ServiceFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceFeeService {

    private final ServiceFeeRepository serviceFeeRepository;


    public Long save(ServiceFeeSaveRequestDto requestDto) {
        return serviceFeeRepository.save(requestDto.toEntity()).getId();
    }

    public List<ServiceFeeResponseDto> findAll() {
        return serviceFeeRepository
                        .findAll()
                        .stream()
                        .filter(fee -> fee.effectiveDate())
                        .map(ServiceFeeResponseDto::new)
                        .collect(Collectors.toList());
    }
}
