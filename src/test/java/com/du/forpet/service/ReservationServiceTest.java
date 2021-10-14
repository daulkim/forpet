package com.du.forpet.service;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.ReservationResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.dto.ReservationUpdateRequestDto;
import com.du.forpet.repository.ReservationRepository;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @AfterEach
    public void tear_down(){
        reservationRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType("Bath")
                                                    .startTime(reservationTime)
                                                    .endTime(reservationTime.plusHours(3))
                                                    .build();
        Long reservationId = reservationService.save(requestDto);
        ReservationStatus status = reservationService.findById(reservationId).getStatus();

        assertThat(status).isEqualTo(ReservationStatus.P);

    }

    @Test
    public void cancelSuccTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType("Bath")
                                                    .startTime(reservationTime)
                                                    .endTime(reservationTime.plusHours(3))
                                                    .build();
        Long reservationId = reservationService.save(requestDto);

        reservationService.cancel(reservationId);

        ReservationStatus status = reservationService.findById(reservationId).getStatus();
        assertThat(status).isEqualTo(ReservationStatus.C);

    }

    @Test
    public void cancelFailTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusHours(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                .builder()
                .serviceType("Bath")
                .startTime(reservationTime)
                .endTime(reservationTime.plusHours(3))
                .build();
        Long reservationId = reservationService.save(requestDto);

        reservationService.cancel(reservationId);

    }

    @Test
    public void updateSuccTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType("Bath")
                                                    .startTime(reservationTime)
                                                    .endTime(reservationTime.plusHours(3))
                                                    .build();

        Long reservationId = reservationService.save(requestDto);

        ReservationUpdateRequestDto updateDto = ReservationUpdateRequestDto
                                                    .builder()
                                                    .startTime(LocalDateTime.now())
                                                    .endTime(LocalDateTime.now().plusHours(3))
                                                    .build();

        reservationService.update(reservationId, updateDto);

        ReservationResponseDto reservationResponseDto = reservationService.findById(reservationId);

        assertThat(reservationResponseDto.getStartTime()).isBefore(LocalDateTime.now());
        assertThat(reservationResponseDto.getEndTime()).isBefore(LocalDateTime.now().plusHours(3));
        assertThat(reservationResponseDto.getStatus()).isEqualTo(ReservationStatus.P);

    }

    @Test
    public void updateFailTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                .builder()
                .serviceType("Bath")
                .startTime(reservationTime)
                .endTime(reservationTime.plusHours(3))
                .build();

        Long reservationId = reservationService.save(requestDto);

        // 예약 변경 불가능한 상태로 변경
        reservationService.cancel(reservationId);

        ReservationUpdateRequestDto updateDto = ReservationUpdateRequestDto
                                                    .builder()
                                                    .startTime(LocalDateTime.now())
                                                    .endTime(LocalDateTime.now().plusHours(3))
                                                    .build();

        reservationService.update(reservationId, updateDto);

        ReservationResponseDto reservationResponseDto = reservationService.findById(reservationId);

    }
}
