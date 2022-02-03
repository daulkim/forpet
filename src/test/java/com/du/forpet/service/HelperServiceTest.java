package com.du.forpet.service;

import com.du.forpet.domain.dto.HelperResponseDto;
import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.domain.dto.HelperUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelperServiceTest {

    @Autowired
    private HelperService helperService;

    @Test
    public void saveTest() {
        HelperSaveRequestDto dto = HelperSaveRequestDto
                                    .builder()
                                    .email("test@test.com")
                                    .name("tester")
                                    .build();
    }

    @Test
    public void updateTest() {
        HelperUpdateRequestDto requestDto = HelperUpdateRequestDto.builder()
                                                                    .name("modifiedName")
                                                                    .build();
        helperService.update(1L, requestDto);
    }

    @Test
    public void withdraw_success_test() {
        // status 가 0 or 2 일 때 => expect: 성공
        helperService.withdraw(1L);
        HelperResponseDto savedHelper = helperService.findById(1L);
        Assertions.assertThat(savedHelper.getStatus()).isEqualTo(1);
    }

    @Test
    public void withdraw_fail_test() {
        // status 가 이미 INACTIVE(1) 일 때 => expect: 실패

        helperService.withdraw(1L);
        HelperResponseDto savedHelper = helperService.findById(1L);
    }
}
