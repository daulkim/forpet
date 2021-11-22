package com.du.forpet.service;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import com.du.forpet.repository.ReservationRepositorySupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HelperRepository helperRepository;

    @Autowired
    private ReservationRepositorySupport reservationRepositorySupport;

//    @AfterEach
//    public void tear_down(){
//        reservationRepository.deleteAll();
//    }

    @Test
    public void testQuerydsl() {
        long count = reservationRepositorySupport.countByHelperAndDateTime(5L, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1));
        assertThat(count).isEqualTo(1L);
    }

    @Test
    public void saveTest() {
        LocalDate date = LocalDate.of(2021,11,23);
        LocalTime sTime = LocalTime.of(12,00);

        Helper helper = helperRepository.findById(5L).get();

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType("Bath")
                                                    .reserveDate(date)
                                                    .startTime(sTime)
                                                    .endTime(sTime.plusHours(1))
                                                    .helper(helper)
                                                    .build();

        Long reservationId = reservationService.save(requestDto);
        ReservationStatus status = reservationService.findById(reservationId).getStatus();

        assertThat(status).isEqualTo(ReservationStatus.PRE);

    }

    @Test
    public void saveFailTest() {
        LocalDate date = LocalDate.of(2021,11,23);
        LocalTime sTime = LocalTime.of(12,00);

        Helper helper = helperRepository.findById(5L).get();

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                        .builder()
                                                        .serviceType("Bath")
                                                        .reserveDate(date)
                                                        .startTime(sTime)
                                                        .endTime(sTime.plusHours(1))
                                                        .helper(helper)
                                                        .build();

        Long reservationId = reservationService.save(requestDto);
        ReservationStatus status = reservationService.findById(reservationId).getStatus();

        assertThat(status).isEqualTo(ReservationStatus.PRE);

    }

    @Test
    public void cancelSuccTest() {
        LocalDate date = LocalDate.of(2021,11,20);
        LocalTime sTime = LocalTime.of(12,00);

        Helper helper = helperRepository.findById(5L).get();
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                        .builder()
                                                        .serviceType("Bath")
                                                        .reserveDate(date)
                                                        .startTime(sTime)
                                                        .endTime(sTime.plusHours(1))
                                                        .helper(helper)
                                                        .build();

        Long reservationId = reservationService.save(requestDto);

        reservationService.cancel(reservationId);

        ReservationStatus status = reservationService.findById(reservationId).getStatus();
        assertThat(status).isEqualTo(ReservationStatus.CANCEL);

    }

    @Test
    public void cancelFailTest() {
        LocalDate date = LocalDate.of(2021,11,20);
        LocalTime sTime = LocalTime.of(12,00);

        Helper helper = helperRepository.findById(5L).get();
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                        .builder()
                                                        .serviceType("Bath")
                                                        .reserveDate(date)
                                                        .startTime(sTime)
                                                        .endTime(sTime.plusHours(1))
                                                        .helper(helper)
                                                        .build();

        Long reservationId = reservationService.save(requestDto);

        reservationService.cancel(reservationId);

    }

    @Test
    public void updateSuccTest() {

        LocalDate date = LocalDate.of(2021,11,20);
        LocalTime sTime = LocalTime.of(12,00);

        Helper helper = helperRepository.findById(5L).get();
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                        .builder()
                                                        .serviceType("Bath")
                                                        .reserveDate(date)
                                                        .startTime(sTime)
                                                        .endTime(sTime.plusHours(1))
                                                        .helper(helper)
                                                        .build();

        Long reservationId = reservationService.save(requestDto);

        ReservationUpdateRequestDto updateDto = ReservationUpdateRequestDto
                                                        .builder()
                                                        .startTime(sTime)
                                                        .endTime(sTime.plusHours(3))
                                                        .build();

        reservationService.update(reservationId, updateDto);

        ReservationResponseDto reservationResponseDto = reservationService.findById(reservationId);

        assertThat(reservationResponseDto.getStartTime()).isBefore(LocalTime.now());
        assertThat(reservationResponseDto.getEndTime()).isBefore(LocalTime.now().plusHours(3));
        assertThat(reservationResponseDto.getStatus()).isEqualTo(ReservationStatus.PRE);

    }

    @Test
    public void updateFailTest() {
        LocalTime sTime = LocalTime.of(12,00);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                        .builder()
                                                        .serviceType("Bath")
                                                        .startTime(sTime)
                                                        .endTime(sTime.plusHours(3))
                                                        .build();

        Long reservationId = reservationService.save(requestDto);

        // 예약 변경 불가능한 상태로 변경
        reservationService.cancel(reservationId);

        ReservationUpdateRequestDto updateDto = ReservationUpdateRequestDto
                                                    .builder()
                                                    .startTime(LocalTime.now())
                                                    .endTime(LocalTime.now().plusHours(3))
                                                    .build();

        reservationService.update(reservationId, updateDto);

        ReservationResponseDto reservationResponseDto = reservationService.findById(reservationId);

    }
}
