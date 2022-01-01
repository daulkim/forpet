package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private String password;
    private String name;
    private String phoneNumber;
    private Address address;

    @Builder
    public MemberUpdateRequestDto(String password,
                                  String name,
                                  String phoneNumber,
                                  Address address) {
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
