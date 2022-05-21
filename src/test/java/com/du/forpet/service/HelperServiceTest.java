package com.du.forpet.service;

import com.du.forpet.domain.dto.HelperSaveRequestDto;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.ServiceType;
import com.du.forpet.repository.ServiceTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class HelperServiceTest {

    @Autowired
    private HelperService helperService;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Test
    public void saveTest() {

        ServiceType serviceType = serviceTypeRepository.findById("산책").get();
        Set<ServiceType> serviceTypes = new HashSet<ServiceType>(Arrays.asList(serviceType));
        HelperSaveRequestDto dto = HelperSaveRequestDto.builder()
                                                        .loginId("helper")
                                                        .password("helper")
                                                        .nickname("helper")
                                                        .serviceTypes(serviceTypes)
                                                        .build();
        helperService.save(dto);

    }

//    @Test
//    public void updateTest() {
//        HelperUpdateRequestDto requestDto = HelperUpdateRequestDto.builder()
//                                                                    .name("modifiedName")
//                                                                    .build();
//        helperService.update(1L, requestDto);
//    }
//
//    @Test
//    public void withdraw_success_test() {
//        // status 가 0 or 2 일 때 => expect: 성공
//        helperService.withdraw(1L);
//        HelperResponseDto savedHelper = helperService.findById(1L);
//        Assertions.assertThat(savedHelper.getStatus()).isEqualTo(1);
//    }
//
//    @Test
//    public void withdraw_fail_test() {
//        // status 가 이미 INACTIVE(1) 일 때 => expect: 실패
//
//        helperService.withdraw(1L);
//        HelperResponseDto savedHelper = helperService.findById(1L);
//    }
}
