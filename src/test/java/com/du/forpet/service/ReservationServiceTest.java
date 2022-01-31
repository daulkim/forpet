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

}
