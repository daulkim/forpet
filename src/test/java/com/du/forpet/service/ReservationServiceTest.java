package com.du.forpet.service;

import com.du.forpet.domain.ServiceType;
import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PetRepository petRepository;

    @Transactional
    @Test
    public void save_test() {
        Pet pet = petRepository.findById(1L).get();

        ReservationSaveRequestDto dto = ReservationSaveRequestDto
                                                        .builder()
                                                        .serviceType(ServiceType.BATH)
                                                        .reservationDate(LocalDate.now())
                                                        .startTime(LocalTime.now())
                                                        .endTime(LocalTime.now().plusHours(2))
                                                        .pet(pet)
                                                        .build();

        reservationService.save(dto);
    }
}
