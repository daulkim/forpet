package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Member;
import com.du.forpet.domain.entity.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetSaveRequestDto {

    private String petName;
    private String memo;
    private Member member;

    @Builder
    public PetSaveRequestDto(String petName,
                             String memo,
                             Member member){
        this.petName = petName;
        this.memo = memo;
        this.member = member;
    }

    public Pet toEntity(){
        return Pet.builder()
                    .petName(petName)
                    .memo(memo)
                    .status(ActivityStatus.ACTIVE)
                    .member(member)
                    .build();
    }
}
