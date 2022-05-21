package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Authority;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.ServiceType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class HelperSaveRequestDto {

    private String loginId;
    private String password;
    private String nickname;
    private String phoneNumber;
    private Set<ServiceType> serviceTypes;

    @Builder
    public HelperSaveRequestDto(String loginId,
                                String password,
                                String nickname,
                                String phoneNumber,
                                Set<ServiceType> serviceTypes){
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.serviceTypes = serviceTypes;
    }

    public Helper toEntity(String encodedPassword) {
        Set<Authority> authorities = new HashSet<Authority>(Arrays.asList(new Authority("ROLE_HELPER")));
        return Helper.builder()
                    .loginId(loginId)
                    .password(encodedPassword)
                    .nickname(nickname)
                    .phoneNumber(phoneNumber)
                    .status(ActivityStatus.UNAUTHORIZED)
                    .authorities(authorities)
                    .serviceTypes(serviceTypes)
                    .build();
    }
}
