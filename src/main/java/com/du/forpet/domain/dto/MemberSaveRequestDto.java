package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.Role;
import com.du.forpet.domain.entity.Address;
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
    private Address address;

    @Builder
    public MemberSaveRequestDto(String email,
                                String name,
                                String password,
                                String phoneNumber,
                                Address address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Member toEntity() {
        return Member.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .status(ActivityStatus.ACTIVE)
                    .penalty(0)
                    .role(Role.USER)
                    .address(address)
                    .build();
    }
}
