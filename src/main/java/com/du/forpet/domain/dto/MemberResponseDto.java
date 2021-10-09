package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
