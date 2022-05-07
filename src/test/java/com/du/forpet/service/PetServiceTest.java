package com.du.forpet.service;

import com.du.forpet.domain.dto.PetSaveRequestDto;
import com.du.forpet.domain.entity.Member;
import com.du.forpet.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PetServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PetService petService;

    @Test
    public void save_test() {
        Member member = memberRepository.findById(2L).get();
        PetSaveRequestDto requestDto = PetSaveRequestDto.builder()
                                                        .petName("pet")
                                                        .memo("test")
                                                        .member(member)
                                                        .build();
        petService.save(requestDto);
    }
}