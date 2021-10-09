package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Pet;
import lombok.Getter;

@Getter
public class PetResponseDto {

    private Long id;
    private String name;
    private String furType;
    private String memo;

    public PetResponseDto(Pet entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.furType = entity.getFurType();
        this.memo = entity.getMemo();
    }
}
