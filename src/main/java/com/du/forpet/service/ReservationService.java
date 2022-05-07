package com.du.forpet.service;

import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Long save(ReservationSaveRequestDto reservationSaveRequestDto) {
        return reservationRepository.save(reservationSaveRequestDto.toEntity()).getId();
    }
}
