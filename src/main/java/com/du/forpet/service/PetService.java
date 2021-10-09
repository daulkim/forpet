package com.du.forpet.service;

import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.PetSaveRequestDto;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    public Long save(PetSaveRequestDto requestDto) {

        return petRepository.save(requestDto.toEntity()).getId();
    }

    public PetResponseDto findById(Long id) {

        Pet entity = petRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("pet 을 찾을 수 없습니다. id: "+id));

        return new PetResponseDto(entity);
    }

    public List<PetResponseDto> findAll() {
        return petRepository
                    .findAll()
                    .stream()
                    .map(PetResponseDto::new)
                    .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Pet pet = petRepository
                    .findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("pet 을 찾을 수 없습니다. id: " + id));

        petRepository.delete(pet);

    }
}
