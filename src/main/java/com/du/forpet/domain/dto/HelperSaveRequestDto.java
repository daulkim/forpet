package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Helper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelperSaveRequestDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String helperType;
    private ActivityStatus status;
    private String district;

    @Builder
    public HelperSaveRequestDto(String email,
                                String password,
                                String name,
                                String phoneNumber,
                                String helperType,
                                String district){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.helperType = helperType;
        this.status = ActivityStatus.UNAUTHORIZED;
        this.district = district;
    }

    public Helper toEntity() {
        return Helper.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .helperType(helperType)
                .status(status)
                .district(district)
                .build();
    }
}
