package com.du.forpet.dto;

import com.du.forpet.entity.Helper;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HelperSaveRequestDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String helperType;

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
    }

    public Helper toEntity() {
        return Helper.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .helperType(helperType)
                .build();
    }
}
