package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    private String email;
    private String name;
    private String password;
    private String phoneNumber;

    @Builder
    public MemberSaveRequestDto(String email, String name, String password, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
