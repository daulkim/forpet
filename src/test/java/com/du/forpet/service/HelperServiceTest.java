package com.du.forpet.service;

import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.domain.dto.HelperScheduleSaveRequestDto;
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
        HelperScheduleSaveRequestDto scheduleDto = HelperScheduleSaveRequestDto
                                                                    .builder()
                                                                    .t0900("Y")
                                                                    .t1000("Y")
                                                                    .build();
        helperService.save(dto, scheduleDto);
    }
}
