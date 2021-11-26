package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelperUpdateRequestDto {

    private String name;
    private String phoneNumber;
    private String district;

    @Builder
    public HelperUpdateRequestDto(String name, String phoneNumber, String district) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.district = district;
    }

}
