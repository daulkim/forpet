package com.du.forpet.service;

import com.du.forpet.domain.dto.ReservationSaveRequestDto;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.domain.entity.ServiceType;
import com.du.forpet.repository.HelperRepository;
import com.du.forpet.repository.PetRepository;
import com.du.forpet.repository.ServiceTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HelperRepository helperRepository;

    @Test
    public void save_test() {
        ServiceType serviceType = serviceTypeRepository.findById("산책").get();
        Pet pet = petRepository.findById(1L).get();
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto.builder()
                                                                        .serviceType(serviceType)
                                                                        .pet(pet)
                                                                        .reservationDateTime(LocalDateTime.now())
                                                                        .build();
        reservationService.save(requestDto);
    }

    @Transactional
    @Test
    public void findAllByServiceType_test() {
        // findAll reservation by serviceTypes
        // filter helper status
        List<String> serviceTypes = helperRepository.findById(5L).get().getServiceTypes().stream().map(ServiceType::getServiceName).collect(Collectors.toList());
        reservationService.findAllByServiceType(serviceTypes);
    }
 }