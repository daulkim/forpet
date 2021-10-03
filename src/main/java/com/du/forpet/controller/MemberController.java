package com.du.forpet.controller;

import com.du.forpet.dto.MemberResponseDto;
import com.du.forpet.dto.MemberSaveRequestDto;
import com.du.forpet.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(MemberSaveRequestDto requestDto) throws Exception {
        return memberService.save(requestDto);
    }

}
