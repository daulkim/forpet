package com.du.forpet.service;

import com.du.forpet.domain.ReservationStatus;
import com.du.forpet.domain.dto.PetResponseDto;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.repository.ReservationRepository;
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
    public void save() {
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
}
