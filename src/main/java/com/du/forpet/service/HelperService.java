package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HelperService {

    private final HelperRepository helperRepository;

    @Transactional
    public Long save(HelperSaveRequestDto requestDto, HelperScheduleSaveRequestDto scheduleRequestDto) {
        Helper helper = helperRepository.save(requestDto.toEntity());
        helper.add(scheduleRequestDto, LocalDate.of(0000,01,01), "Y");

        return helper.getId();
    }

    public HelperResponseDto findById(Long id) {
        Helper helper = findByIdOrElseThrowException(id);
        return new HelperResponseDto(helper);
    }

    public Long update(Long id, HelperUpdateRequestDto requestDto) {
        Helper helper = findByIdOrElseThrowException(id);

        helper.update(requestDto.getName(), requestDto.getPhoneNumber());

        return id;
    }

    public void withdraw(Long id) {
        Helper helper = findByIdOrElseThrowException(id);
        helper.withdraw(id);
    }

    public Helper findByIdOrElseThrowException(Long id) {
        return helperRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다."+id));
    }

    public List<ReservationListResponseDto> findReservationsById(Long id) {
        Helper helper = helperRepository
                            .findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다." + id));

        List<ReservationListResponseDto> reservationList = helper
                                                            .getReservations()
                                                            .stream()
                                                            .map(ReservationListResponseDto::new)
                                                            .collect(Collectors.toList());
        return reservationList;
    }
}
