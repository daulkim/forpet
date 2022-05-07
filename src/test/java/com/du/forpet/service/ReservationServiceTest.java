package com.du.forpet.service;

import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.entity.ServiceType;
import com.du.forpet.repository.ServiceTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ReservationService reservationService;

    @Test
    public void save_test() {
        ServiceType serviceType = serviceTypeRepository.findById(1L).get();
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto.builder()
                                                                        .serviceType(serviceType)
                                                                        .reservationTime(LocalDateTime.now())
                                                                        .build();
        reservationService.save(requestDto);
    }
}