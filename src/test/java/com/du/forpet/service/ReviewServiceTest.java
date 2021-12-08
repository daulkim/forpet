package com.du.forpet.service;

import com.du.forpet.domain.dto.ReviewSaveRequestDto;
import com.du.forpet.domain.entity.Reservation;
import com.du.forpet.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void saveTest() {
        Reservation reservation = reservationRepository.findById(33L).get();
        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto
                                            .builder()
                                            .rating(3.5f)
                                            .comment("good")
                                            .reservation(reservation)
                                            .build();


        reviewService.save(requestDto);
    }
}
