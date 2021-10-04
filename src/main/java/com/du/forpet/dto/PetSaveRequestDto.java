package com.du.forpet.dto;

import com.du.forpet.entity.Member;
import com.du.forpet.entity.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetSaveRequestDto {

    private String name;
    private String memo;
    private Member member;

    @Builder
    public PetSaveRequestDto(String name, String memo, Member member){
        this.name = name;
        this.memo = memo;
        this.member = member;
    }

    public Pet toEntity(){
        return Pet.builder()
                .name(name)
                .memo(memo)
                .member(member)
                .build();
    }
}
