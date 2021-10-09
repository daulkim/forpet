package com.du.forpet.service;

import com.du.forpet.domain.dto.PetSaveRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

import com.du.forpet.domain.entity.Member;
import com.du.forpet.domain.entity.Pet;
import com.du.forpet.repository.MemberRepository;
import com.du.forpet.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PetServiceTest {

    @Autowired
    private  PetService petService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void save_pet(){
        Member member = Member.builder()
                                .name("member1")
                                .build();
        memberRepository.save(member);

        PetSaveRequestDto petSaveRequestDto = PetSaveRequestDto.builder()
                                                                .name("name")
                                                                .memo("memo")
                                                                .member(member)
                                                                .build();
        Long savedId = petService.save(petSaveRequestDto);

        List<Pet> pets =  petRepository.findAll();

        assertThat(pets.get(0).getId()).isEqualTo(savedId);

    }
}
