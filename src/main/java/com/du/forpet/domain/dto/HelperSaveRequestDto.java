package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.HelperSchedule;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class HelperSaveRequestDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String helperType;
    private ActivityStatus status;

    @Builder
    public HelperSaveRequestDto(String email,
                                String password,
                                String name,
                                String phoneNumber,
                                String helperType){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.helperType = helperType;
        this.status = ActivityStatus.ACTIVE;
    }

    public Helper toEntity() {
        return Helper.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .helperType(helperType)
                .status(status)
                .build();
    }
}
