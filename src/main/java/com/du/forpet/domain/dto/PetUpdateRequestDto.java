package com.du.forpet.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PetUpdateRequestDto {

    private String name;
    private String memo;

    @Builder
    public PetUpdateRequestDto(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }
}
