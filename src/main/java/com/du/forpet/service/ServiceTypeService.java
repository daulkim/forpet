package com.du.forpet.service;

import com.du.forpet.domain.dto.ServiceTypeResponseDto;
import com.du.forpet.domain.dto.ServiceTypeSaveRequestDto;
import com.du.forpet.repository.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    public String save(ServiceTypeSaveRequestDto serviceTypeSaveRequestDto) {
        return serviceTypeRepository.save(serviceTypeSaveRequestDto.toEntity()).getServiceName();
    }

    public List<ServiceTypeResponseDto> findAllServiceName() {
        return serviceTypeRepository.findAll()
                                    .stream()
                                    .map(serviceType -> new ServiceTypeResponseDto(serviceType))
                                    .collect(Collectors.toList());
    }
}
