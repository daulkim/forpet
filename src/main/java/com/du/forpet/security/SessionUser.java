package com.du.forpet.security;

import com.du.forpet.domain.entity.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String loginId;
    private String nickname;

    public SessionUser(Member member){
        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();
    }
}