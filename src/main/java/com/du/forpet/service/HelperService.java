package com.du.forpet.service;

import com.du.forpet.domain.dto.HelperResponseDto;
import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HelperService {

    private final HelperRepository helperRepository;

    public Long save(HelperSaveRequestDto requestDto) {
        return helperRepository.save(requestDto.toEntity()).getId();
    }


    public HelperResponseDto findById(Long id) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(
                                ()-> new IllegalArgumentException("해당 아이디의 helper가 존재하지 않습니다."+id));
        return new HelperResponseDto(helper);
    }
}
