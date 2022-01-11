package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.HelperSchedule;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.ReservationHistory;
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
    private final MailService mailService;

    @Transactional
    public Long save(ReservationSaveRequestDto requestDto) {

        HelperSchedule helperSchedule = getHelperSchedule(requestDto.getHelper(), requestDto.getStartTime(), "F");
        boolean isReserved = !helperSchedule.checkTimeAvailability(requestDto.getStartTime(), requestDto.getEndTime());
        boolean isPenaltyMember = requestDto.getPet().checkPenalty();

        if (isReserved||isPenaltyMember) throw new RuntimeException("해당 시간은 예약이 불가능한 시간입니다.");

        helperSchedule.reserveSchedule(requestDto.getStartTime(), requestDto.getEndTime());

        Reservation savedReservation = reservationRepository.save(requestDto.toEntity());
        Long id = savedReservation.getId();
        savedReservation.addHistory(ReservationHistory
                                        .builder()
                                        .status(savedReservation.getStatus())
                                        .reservation(savedReservation)
                                        .build());
        // pay 완료

        // mail 발송
        mailService.sendMail(MailSendDto.builder()
                    .receiver(requestDto.getPet().getMember().getEmail())
                    .subject("예약 요청")
                    .text("예약번호:" + id + "의 예약이 요청되었습니다.")
                    .build());

        return id;
    }

    @Transactional
    public ReservationResponseDto findById(Long id) {

        Reservation reservation = findByIdOrElseThrowException(id);
        return new ReservationResponseDto(reservation);
    }

    @Transactional
    public void cancel(Long id, String memo) {

        Reservation reservation = findByIdOrElseThrowException(id);
        HelperSchedule helperSchedule = getHelperSchedule(reservation.getHelper(),
                                                            reservation.getStartTime(),
                                                            "cancel");

        helperSchedule.cancelSchedule(reservation.getStartTime(), reservation.getEndTime());
        reservation.cancel(memo);
        reservation.addHistory(ReservationHistory
                                    .builder()
                                    .status(reservation.getStatus())
                                    .reservation(reservation)
                                    .build());
    }

    @Transactional
    public Long update(Long id, ReservationUpdateRequestDto requestDto) {

        Reservation reservation = findByIdOrElseThrowException(id);
        HelperSchedule helperSchedule = getHelperSchedule(reservation.getHelper(),
                                                            requestDto.getStartTime(),
                                                            "update");
        boolean isReserved = !helperSchedule.checkTimeAvailability(requestDto.getStartTime(),
                                                                    requestDto.getEndTime());

        if (isReserved) throw new RuntimeException("해당 시간은 예약이 불가능한 시간입니다.");

        helperSchedule.reserveSchedule(requestDto.getStartTime(), requestDto.getEndTime());
        reservation.update(requestDto.getStartTime(), requestDto.getEndTime());

        return id;
    }

    public Long approve(Long id) {

        Reservation reservation = findByIdOrElseThrowException(id);
        reservation.approve();
        return id;
    }

    private Reservation findByIdOrElseThrowException(Long id){

        return reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));
    }

    private HelperSchedule getHelperSchedule(Helper helper, LocalDateTime startTime, String type) {

        String errMsg = type.equalsIgnoreCase("cancel")? "해당일의 예약이 존재하지 않습니다.":"해당일은 예약이 불가능합니다.";
        return helperScheduleRepository
                .findByHelper_idAndDate(helper.getId(), startTime.toLocalDate())
                .orElseThrow(() -> new IllegalArgumentException(errMsg));
    }
}
