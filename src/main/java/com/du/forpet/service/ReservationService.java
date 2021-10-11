package com.du.forpet.service;

import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Long save(ReservationSaveRequestDto requestDto) {

        return reservationRepository.save(requestDto.toEntity()).getId();

    }

    public ReservationResponseDto findById(Long id) {

        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 아이디의 예약이 존재하지 않습니다." + id));

        return new ReservationResponseDto(reservation);
    }

    @Transactional
    public void cancel(Long id) {
        Reservation reservation = reservationRepository
                                    .findById(id)
                                    .orElseThrow(() ->
                                            new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));

        reservation.cancel();

    }

    @Transactional
    public Long update(Long id, ReservationUpdateRequestDto requestDto) {
        Reservation reservation = reservationRepository
                                    .findById(id)
                                    .orElseThrow(() ->
                                            new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));

        reservation.update(requestDto.getStartTime(), requestDto.getEndTime());

        return id;
    }
}
