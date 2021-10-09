package com.du.forpet.service;

import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HelperService {

    private final HelperRepository helperRepository;

    /**
     * helper join
     */
    public Long save(HelperSaveRequestDto requestDto) {
        return helperRepository.save(requestDto.toEntity()).getId();
    }
}
