package com.du.forpet.domain.dto;

import com.du.forpet.domain.ActivityStatus;
import com.du.forpet.domain.entity.Authority;
import com.du.forpet.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

    private String loginId;
    private String nickname;
    private String password;
    private String phoneNumber;

    @Builder
    public MemberSaveRequestDto(String loginId,
                                String nickname,
                                String password,
                                String phoneNumber) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Member toEntity(String encodedPassword) {
        Set<Authority> authorities = new HashSet<Authority>(Arrays.asList(new Authority("ROLE_USER")));
        return Member.builder()
                    .loginId(loginId)
                    .nickname(nickname)
                    .password(encodedPassword)
                    .phoneNumber(phoneNumber)
                    .status(ActivityStatus.ACTIVE)
                    .authorities(authorities)
                    .build();
    }
}
