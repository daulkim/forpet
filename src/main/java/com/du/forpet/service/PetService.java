package com.du.forpet.service;

import com.du.forpet.domain.dto.PetSaveRequestDto;
import com.du.forpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    public Long save(PetSaveRequestDto petSaveRequestDto) {
        return petRepository.save(petSaveRequestDto.toEntity()).getId();
    }
}
