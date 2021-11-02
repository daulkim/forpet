package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.HelperSchedule;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.repository.HelperScheduleRepository;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HelperScheduleRepository helperScheduleRepository;

    @Transactional
    public Long save(ReservationSaveRequestDto requestDto) {

        HelperSchedule helperSchedule = helperScheduleRepository
                                            .findByHelper_idAndDate(requestDto.getHelper().getId(),
                                                            requestDto.getStartTime().toLocalDate())
                                                            .orElseThrow(()->
                                                                    new IllegalArgumentException("해당 일은 예약이 불가능합니다."));

        boolean isReserved = !helperSchedule.checkTimeAvailability(requestDto.getStartTime(), requestDto.getEndTime());

        if (isReserved) {
            throw new RuntimeException("해당 시간은 예약이 불가능한 시간입니다.");
        }

        helperSchedule.reserveSchedule(requestDto.getStartTime(), requestDto.getEndTime());

        return reservationRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public ReservationResponseDto findById(Long id) {

        Reservation reservation = reservationRepository
                                        .findById(id)
                                        .orElseThrow(() ->
                                                new IllegalArgumentException("해당 아이디의 예약이 존재하지 않습니다." + id));

        return new ReservationResponseDto(reservation);
    }

    @Transactional
    public List<ReservationListResponseDto> findByHelperEmail(String email) {

        return reservationRepository.findByHelper_email(email)
                                    .stream()
                                    .map(ReservationListResponseDto::new)
                                    .collect(Collectors.toList());
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
