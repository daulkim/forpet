package com.du.forpet.service;

import com.du.forpet.domain.dto.BathReservationSaveRequestDto;
import com.du.forpet.domain.entity.BathReservation;
import com.du.forpet.repository.BathReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BathReservationService {

    private final BathReservationRepository bathReservationRepository;

    public Long save(BathReservationSaveRequestDto requestDto) {

        return bathReservationRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public void cancel(Long id) {
        BathReservation bathReservation = bathReservationRepository
                                                .findById(id)
                                                .orElseThrow(() ->
                                                        new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));

        bathReservation.cancel();

    }

}
