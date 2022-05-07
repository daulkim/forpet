package com.du.forpet.service;

import com.du.forpet.domain.dto.MemberSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void save_test() {
        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                                                            .loginId("test")
                                                            .password("test")
                                                            .nickname("tester")
                                                            .build();
        memberService.save(requestDto);

    }
}
