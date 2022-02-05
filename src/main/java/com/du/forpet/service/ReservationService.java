package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.domain.entity.ReservationHistory;
import com.du.forpet.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MailService mailService;
    private final PayService payService;

    @Transactional
    public Long save(ReservationSaveRequestDto reservationReqDto, PaySaveRequestDto payReqDto) {

        boolean isPenaltyMember = reservationReqDto.getPet().checkPenalty();

        if(isPenaltyMember)
            throw new RuntimeException("해당 회원은 예약이 불가능한 상태입니다.");
        
        Reservation savedReservation = reservationRepository.save(reservationReqDto.toEntity());
        Long id = savedReservation.getId();
        savedReservation.addHistory(ReservationHistory
                                        .builder()
                                        .status(savedReservation.getStatus())
                                        .reservation(savedReservation)
                                        .build());
        // pay 완료
        payService.save(payReqDto);

        // mail 발송
        mailService.sendMail(MailSendDto.builder()
                                        .receiver(reservationReqDto.getPet().getMember().getEmail())
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
        reservation.update(requestDto.getReservationDate(),
                            requestDto.getStartTime(),
                            requestDto.getEndTime());

        return id;
    }

    public Long approve(Long id) {

        Reservation reservation = findByIdOrElseThrowException(id);
        reservation.approve(id);
        return id;
    }

    private Reservation findByIdOrElseThrowException(Long id){

        return reservationRepository
                    .findById(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException("해당 예약을 찾을 수 없습니다. id: " + id));
    }
}
