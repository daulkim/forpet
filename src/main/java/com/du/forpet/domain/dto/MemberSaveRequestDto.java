package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private int penalty;

    @Builder
    public MemberSaveRequestDto(String email, String name, String password, String phoneNumber, int penalty) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.penalty = 0;
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
