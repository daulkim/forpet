package com.du.forpet.service;

import com.du.forpet.domain.dto.ServiceFeeResponseDto;
import com.du.forpet.domain.dto.ServiceFeeSaveRequestDto;
import com.du.forpet.domain.dto.ServiceFeeUpdateRequestDto;
import com.du.forpet.domain.entity.ServiceFee;
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

    public List<ServiceFeeResponseDto> findEffectiveAll() {
        return serviceFeeRepository
                        .findAll()
                        .stream()
                        .filter(fee -> fee.effectiveDate())
                        .map(ServiceFeeResponseDto::new)
                        .collect(Collectors.toList());
    }

    public Long update(Long id, ServiceFeeUpdateRequestDto requestDto) {
        ServiceFee serviceFee = serviceFeeRepository
                                    .findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("해당요금이 존재하지 않습니다. id : " + id));

        serviceFee.update(requestDto.getServiceType(),
                            requestDto.getFee(),
                            requestDto.getStartDate(),
                            requestDto.getEndDate());
        return id;
    }
}
