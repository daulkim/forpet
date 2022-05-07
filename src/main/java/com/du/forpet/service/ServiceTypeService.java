package com.du.forpet.service;

import com.du.forpet.domain.dto.ServiceTypeSaveRequestDto;
import com.du.forpet.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public Long save(ServiceTypeSaveRequestDto serviceTypeSaveRequestDto) {
        return serviceTypeRepository.save(serviceTypeSaveRequestDto.toEntity()).getId();
    }
}
