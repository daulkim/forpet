package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.BathReservation;
import com.du.forpet.domain.entity.Pet;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
public class BathReservationSaveRequestDto {

    private String serviceType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Pet pet;

    @Builder
    public BathReservationSaveRequestDto(String serviceType,
                                         LocalDateTime startTime,
                                         LocalDateTime endTime,
                                         Pet pet) {

        this.serviceType = serviceType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pet = pet;

    }

    public BathReservation toEntity() {

        return BathReservation
                        .builder()
                        .serviceType(serviceType)
                        .startTime(startTime)
                        .endTime(endTime)
                        .pet(pet)
                        .build();
    }
}
