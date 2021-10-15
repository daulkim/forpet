package com.du.forpet.domain.dto;

import com.du.forpet.domain.PetStatus;
import com.du.forpet.domain.entity.Member;
import com.du.forpet.domain.entity.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetSaveRequestDto {

    private String name;
    private String memo;
    private Member member;
    private PetStatus status;

    @Builder
    public PetSaveRequestDto(String name, String memo, Member member){
        this.name = name;
        this.memo = memo;
        this.status = PetStatus.ACTIVE;
        this.member = member;
    }

    public Pet toEntity(){
        return Pet.builder()
                    .name(name)
                    .memo(memo)
                    .status(status)
                    .member(member)
                    .build();
    }
}
