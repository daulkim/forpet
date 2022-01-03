package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Helper;
import lombok.Getter;

@Getter
public class HelperResponseDto {

    private String email;
    private String name;
    private String helperType;
    private String phoneNumber;
    private ActivityStatus status;

    public HelperResponseDto(Helper entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.helperType = entity.getHelperType();
        this.phoneNumber = entity.getPhoneNumber();
        this.status = entity.getStatus();
    }
}
