package com.du.forpet.service;

import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.PetSaveRequestDto;
import com.du.forpet.domain.dto.PetUpdateRequestDto;
import com.du.forpet.domain.entity.Member;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    public Long save(PetSaveRequestDto requestDto) {

        return petRepository.save(requestDto.toEntity()).getId();
    }

    public PetResponseDto findById(Long id) {

        Pet pet = findByIdOrElseThrowException(id);
        return new PetResponseDto(pet);
    }

    public List<PetResponseDto> findAll() {

        return petRepository
                    .findAll()
                    .stream()
                    .map(PetResponseDto::new)
                    .collect(Collectors.toList());
    }

    @Transactional
    public Long updateInfo(Long id, PetUpdateRequestDto requestDto) {

        Pet pet = findByIdOrElseThrowException(id);
        pet.update(requestDto.getName(), requestDto.getMemo());

        return id;

    }

    @Transactional
    public void withdraw(Long id) {

        Pet pet = findByIdOrElseThrowException(id);
        pet.withdraw();

    }

    private Pet findByIdOrElseThrowException(Long id) {

        return petRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("pet 을 찾을 수 없습니다. id: " + id));
    }
}
