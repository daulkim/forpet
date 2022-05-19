package com.du.forpet.service;

import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private Logger logger = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository reservationRepository;

    public Long save(ReservationSaveRequestDto reservationSaveRequestDto) {
        return reservationRepository.save(reservationSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public ReservationResponseDto findById(Long id) {
        Reservation reservation = findByIdOrElseThrowException(id);
        return new ReservationResponseDto(reservation);
    }

    @Transactional
    public Long update(Long id, ReservationUpdateRequestDto requestDto) {

        Reservation reservation = findByIdOrElseThrowException(id);
        logger.info("update date time : {}", requestDto.getReservationDateTime());
        reservation.update(requestDto.getReservationDateTime());

        return id;
    }

    @Transactional
    public Long cancel(Long id) {

        Reservation reservation = findByIdOrElseThrowException(id);
        reservation.cancel();

        return reservation.getId();
    }

    private Reservation findByIdOrElseThrowException(Long id){

        return reservationRepository.findById(id)
                                    .orElseThrow(() ->
                                            new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));
    }
}
