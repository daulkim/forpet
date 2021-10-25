package com.du.forpet.service;

import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
import com.du.forpet.domain.dto.HelperUpdateRequestDto;
import com.du.forpet.domain.dto.HelperResponseDto;
import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import com.du.forpet.repository.HelperScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HelperService {

    private final HelperRepository helperRepository;

    private final HelperScheduleRepository helperScheduleRepository;

    @Transactional
    public Long save(HelperSaveRequestDto requestDto, HelperScheduleSaveRequestDto scheduleRequestDto) {
        Helper helper = helperRepository.save(requestDto.toEntity());
        helper.setHelperSchedule(helper, scheduleRequestDto);
        return helper.getId();
    }


    public HelperResponseDto findById(Long id) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(
                                ()-> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다."+id));
        return new HelperResponseDto(helper);
    }

    public Long update(Long id, HelperUpdateRequestDto requestDto) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(
                        ()-> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다."+id));

        helper.update(requestDto.getName(), requestDto.getPhoneNumber());

        return id;
    }

    public void delete(Long id) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(
                        ()-> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다."+id));
        helper.resign(id);
    }
}
