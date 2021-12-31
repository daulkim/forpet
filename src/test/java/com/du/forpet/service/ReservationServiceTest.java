package com.du.forpet.service;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.repository.HelperRepository;
import com.du.forpet.repository.PetRepository;
import com.du.forpet.repository.ReservationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HelperRepository helperRepository;

    @Autowired
    private PetRepository petRepository;

//    @AfterEach
//    public void tear_down(){
//        reservationRepository.deleteAll();
//    }

//    @Test
//    public void testQuerydsl() {
//        long count = reservationRepositorySupport.countByHelperAndDateTime(5L);
//        assertThat(count).isEqualTo(1L);
//    }
    @Test
    @Transactional
    public void saveTest() {
        LocalDateTime reservationTime = LocalDateTime.of(2022,01,02,10,00);

        Helper helper = helperRepository.findById(1L).get();
        Pet pet = petRepository.findById(1L).get();

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType(ServiceType.BATH)
                                                    .startTime(reservationTime)
                                                    .endTime(reservationTime.plusHours(1))
                                                    .helper(helper)
                                                    .pet(pet)
                                                    .build();
        Long reservationId = reservationService.save(requestDto);
        ReservationStatus status = reservationService.findById(reservationId).getStatus();

        assertThat(status).isEqualTo(ReservationStatus.REQ);

    }

    @Test
    public void cancelSuccTest() {
        LocalDateTime reservationTime = LocalDateTime.of(2021,11,15,13,00);

        Helper helper = helperRepository.findById(1L).get();
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType(ServiceType.BATH)
                                                    .startTime(reservationTime)
                                                    .endTime(reservationTime.plusHours(1))
                                                    .helper(helper)
                                                    .build();
        Long reservationId = reservationService.save(requestDto);

        reservationService.cancel(reservationId, "memo");

        ReservationStatus status = reservationService.findById(reservationId).getStatus();
        assertThat(status).isEqualTo(ReservationStatus.CANCEL);

    }

    @Test
    public void cancelFailTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusHours(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType(ServiceType.BATH)
                                                    .startTime(reservationTime)
                                                    .endTime(reservationTime.plusHours(3))
                                                    .build();
        Long reservationId = reservationService.save(requestDto);

        reservationService.cancel(reservationId, "memo");

    }

    @Test
    public void updateSuccTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                                                    .builder()
                                                    .serviceType(ServiceType.BATH)
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
        assertThat(reservationResponseDto.getStatus()).isEqualTo(ReservationStatus.REQ);

    }

    @Test
    public void updateFailTest() {
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(3);

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto
                .builder()
                .serviceType(ServiceType.BATH)
                .startTime(reservationTime)
                .endTime(reservationTime.plusHours(3))
                .build();

        Long reservationId = reservationService.save(requestDto);

        // 예약 변경 불가능한 상태로 변경
        reservationService.cancel(reservationId, "memo");

        ReservationUpdateRequestDto updateDto = ReservationUpdateRequestDto
                                                    .builder()
                                                    .startTime(LocalDateTime.now())
                                                    .endTime(LocalDateTime.now().plusHours(3))
                                                    .build();

        reservationService.update(reservationId, updateDto);

        ReservationResponseDto reservationResponseDto = reservationService.findById(reservationId);

    }
}
