package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelperUpdateRequestDto {

    private String name;
    private String phoneNumber;

    @Builder
    public HelperUpdateRequestDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}
