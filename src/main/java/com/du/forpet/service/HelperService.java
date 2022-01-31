package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HelperService {

    private final HelperRepository helperRepository;

    @Transactional
    public Long save(HelperSaveRequestDto requestDto) {
        Helper helper = helperRepository.save(requestDto.toEntity());

        return helper.getId();
    }

    public HelperResponseDto findById(Long id) {
        Helper helper = findByIdOrElseThrowException(id);
        return new HelperResponseDto(helper);
    }

    @Transactional
    public Long update(Long id, HelperUpdateRequestDto requestDto) {
        Helper helper = findByIdOrElseThrowException(id);

        helper.update(requestDto.getName(),
                        requestDto.getPhoneNumber(),
                        requestDto.getDistrict());

        return id;
    }

    @Transactional
    public void withdraw(Long id) {
        Helper helper = findByIdOrElseThrowException(id);
        helper.withdraw(id);
    }

    @Transactional
    public List<ReservationListResponseDto> findReservationsById(Long id) {
        Helper helper = findByIdOrElseThrowException(id);
        List<ReservationListResponseDto> reservationList = helper
                                                            .getReservations()
                                                            .stream()
                                                            .map(ReservationListResponseDto::new)
                                                            .collect(Collectors.toList());
        return reservationList;
    }

    private Helper findByIdOrElseThrowException(Long id) {
        return helperRepository
                    .findById(id)
                    .orElseThrow(
                            ()-> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다. id: " + id));
    }
}
