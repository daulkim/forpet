package com.du.forpet.service;

import com.du.forpet.domain.dto.ServiceTypeSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTypeServiceTest {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Test
    public void save_test() {
        ServiceTypeSaveRequestDto requestDto = ServiceTypeSaveRequestDto.builder()
                                                                        .serviceName("목욕")
                                                                        .serviceFee(10000)
                                                                        .build();
        serviceTypeService.save(requestDto);
    }
}