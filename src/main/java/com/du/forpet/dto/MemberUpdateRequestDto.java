package com.du.forpet.dto;

import com.du.forpet.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    private String password;
    private String name;
    private String phoneNumber;

    @Builder
    public MemberUpdateRequestDto(String password, String name, String phoneNumber) {
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
