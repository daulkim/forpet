package com.du.forpet.service;

import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository bathReservationRepository;

    public Long save(ReservationSaveRequestDto requestDto) {

        return bathReservationRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public void cancel(Long id) {
        Reservation reservation = bathReservationRepository
                                                .findById(id)
                                                .orElseThrow(() ->
                                                        new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));

        reservation.cancel();

    }

}
