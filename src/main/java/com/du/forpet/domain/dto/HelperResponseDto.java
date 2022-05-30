package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Authority;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.domain.entity.ServiceType;
import lombok.Getter;

import java.util.Set;

@Getter
public class HelperResponseDto {

    private String loginId;
    private String password;
    private String nickname;
    private ActivityStatus status;
    private Set<ServiceType> serviceTypes;
    private Set<Authority> authorities;

    public HelperResponseDto(Helper entity) {
        this.loginId = entity.getLoginId();
        this.password = entity.getPassword();
        this.nickname = entity.getNickname();
        this.status = entity.getStatus();
        this.serviceTypes = ServiceType.serviceTypeList(entity.getServiceTypes());
        this.authorities = Authority.authoritySet(entity.getAuthorities());
    }
}
