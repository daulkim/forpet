package com.du.forpet.domain.dto;

import com.du.forpet.domain.entity.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetResponseDto {

    private Long id;
    private String name;

    @Builder
    public PetResponseDto(Pet entity) {
        this.id = entity.getId();
        this.name = entity.getPetName();
    }
}
