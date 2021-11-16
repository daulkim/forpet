package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.HelperSchedule;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.repository.HelperScheduleRepository;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HelperScheduleRepository helperScheduleRepository;

    @Transactional
    public Long save(ReservationSaveRequestDto requestDto) {

        HelperSchedule helperSchedule = getHelperSchedule(requestDto.getHelper(), requestDto.getStartTime(), "F");
        boolean isReserved = !helperSchedule.checkTimeAvailability(requestDto.getStartTime(), requestDto.getEndTime());

        if (isReserved) throw new RuntimeException("해당 시간은 예약이 불가능한 시간입니다.");

        helperSchedule.reserveSchedule(requestDto.getStartTime(), requestDto.getEndTime());
        return reservationRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public ReservationResponseDto findById(Long id) {
        Reservation reservation = getReservation(id);
        return new ReservationResponseDto(reservation);
    }

    @Transactional
    public void cancel(Long id) {
        Reservation reservation = getReservation(id);
        HelperSchedule helperSchedule = getHelperSchedule(reservation.getHelper(), reservation.getStartTime(),"C");
        helperSchedule.cancelSchedule(reservation.getStartTime(), reservation.getEndTime());
        reservation.cancel();
    }

    @Transactional
    public Long update(Long id, ReservationUpdateRequestDto requestDto) {
        Reservation reservation = getReservation(id);
        HelperSchedule helperSchedule = getHelperSchedule(reservation.getHelper(), requestDto.getStartTime(), "F");
        boolean isReserved = !helperSchedule.checkTimeAvailability(requestDto.getStartTime(), requestDto.getEndTime());

        if (isReserved) throw new RuntimeException("해당 시간은 예약이 불가능한 시간입니다.");

        helperSchedule.reserveSchedule(requestDto.getStartTime(), requestDto.getEndTime());
        reservation.update(requestDto.getStartTime(), requestDto.getEndTime());

        return id;
    }

    private Reservation getReservation(Long id){
        return reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));
    }

    private HelperSchedule getHelperSchedule(Helper helper, LocalDateTime startTime, String type) {
        String errMsg = type.equals("C")?"해당일의 예약이 존재하지 않습니다.":"해당일은 예약이 불가능합니다.";
        return helperScheduleRepository
                .findByHelper_idAndDate(helper.getId(),
                                        startTime.toLocalDate())
                                        .orElseThrow(()->
                                            new IllegalArgumentException(errMsg));
    }
}
